package com.discernible.message.body.type3;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EsnField implements Field {

  private String esn;

  public static EsnField decode(Queue<Byte> messageBytes) {

    byte[] esnBytes = ByteUtils.getFieldBytes(8, messageBytes);

    StringBuilder esnBuilder = new StringBuilder();
    for (byte theByte : esnBytes) {
      esnBuilder.append(getTwoNumbersFromByte(theByte));
    }

    String esn = esnBuilder.substring(6, 16); // Throw away the some characters as they are not used.
    return new EsnField(esn);
  }

  @Override
  public byte[] encode() {
    String esnHexString = "000000" + esn;

    return hexStringToByteArray(esnHexString);
  }

  private static String getTwoNumbersFromByte(byte theByte) {

    String binary = getBinaryForByte(theByte);

    String upperNumberBinary = binary.substring(0, 4);
    String lowerNumberBinary = binary.substring(4, 8);

    Integer upperNumber = Integer.parseInt(upperNumberBinary, 2);
    Integer lowerNumber = Integer.parseInt(lowerNumberBinary, 2);

    return upperNumber.toString() + lowerNumber.toString();
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
