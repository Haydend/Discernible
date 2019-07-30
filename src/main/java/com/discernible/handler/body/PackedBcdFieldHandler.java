package com.discernible.handler.body;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class PackedBcdFieldHandler implements FieldHandler<String> {

  public int fieldLength;

  public PackedBcdFieldHandler(int fieldLength) {
    this.fieldLength = fieldLength;
  }

  @Override
  public String decode(Queue<Byte> messageBytes) {

    byte[] bytes = ByteUtils.getFieldBytes(fieldLength, messageBytes);

    StringBuilder valueBuilder = new StringBuilder();
    for (byte theByte : bytes) {
      valueBuilder.append(getTwoNumbersFromByte(theByte));
    }

    String value = valueBuilder.toString();
    return value;
  }

  @Override
  public void encode(String value, ByteOutputStream output) {

    String hexString = value;

    while ((hexString.length() / 2) < fieldLength) {
      hexString += "F"; // Add padding to the until the length is correct.
    }

    output.write(hexStringToByteArray(hexString));
  }

  private static String getTwoNumbersFromByte(byte theByte) {

    String binary = getBinaryForByte(theByte);

    String upperNumberBinary = binary.substring(0, 4);
    String lowerNumberBinary = binary.substring(4, 8);

    Integer upperNumber = Integer.parseInt(upperNumberBinary, 2);
    Integer lowerNumber = Integer.parseInt(lowerNumberBinary, 2);

    String completeString = "";
    if (upperNumber != 15) {
      completeString += upperNumber.toString();
    }

    if (lowerNumber != 15) {
      completeString += lowerNumber.toString();
    }

    return completeString;
  }

  private static String getBinaryForByte(byte theByte) {
    return String.format("%8s", Integer.toBinaryString(theByte & 0xFF)).replace(' ', '0');
  }

  private static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
          + Character.digit(s.charAt(i + 1), 16));
    }
    return data;
  }

}
