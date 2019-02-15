package com.discernible.message.body;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class PackedBcdField implements Field {

  private String value;

  public int fieldLength;

  public PackedBcdField(String value, int fieldLength) {

    this.value = value;
    this.fieldLength = fieldLength;

    if ((value.length() / 2) > fieldLength) {
      throw new IllegalArgumentException(String.format("Field value '%s' will not fit in field length %s", value, fieldLength));
    }

  }

  public static PackedBcdField decode(Queue<Byte> messageBytes, int fieldLength) {

    byte[] imeiBytes = ByteUtils.getFieldBytes(fieldLength, messageBytes);

    StringBuilder imeiBuilder = new StringBuilder();
    for (byte theByte : imeiBytes) {
      imeiBuilder.append(getTwoNumbersFromByte(theByte));
    }

    String imei = imeiBuilder.toString();
    return new PackedBcdField(imei, fieldLength);
  }

  @Override
  public byte[] encode() {

    String hexString = value;

    while ((hexString.length() / 2) < fieldLength) {
      hexString += "F"; // Add padding to the until the length is correct.
    }

    return hexStringToByteArray(hexString);
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
