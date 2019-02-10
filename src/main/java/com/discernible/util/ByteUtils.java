package com.discernible.util;

import java.util.Queue;

public class ByteUtils {

  private ByteUtils() {
  };

  public static final int unsignedShortToInt(byte[] b) {
    int l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
    return l;
  }

  public static final long unsignedIntToLong(byte[] b) {
    long l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
    l <<= 8;
    l |= b[2] & 0xFF;
    l <<= 8;
    l |= b[3] & 0xFF;
    return l;
  }

  public static byte[] prependFieldLength(byte[] fieldBytes) {
    // Get the field length that need prepending the byte array.
    byte fieldLength = (byte) fieldBytes.length;

    // Build a new byte array, with the field length byte first, then the field bytes;
    byte[] messageBytes = new byte[fieldBytes.length + 1];
    messageBytes[0] = fieldLength;
    System.arraycopy(fieldBytes, 0, messageBytes, 1, fieldBytes.length);

    return messageBytes;
  }

  public static int getFieldLength(Queue<Byte> messageBytes) {
    int fieldLength = messageBytes.poll();
    return fieldLength;
  }

  public static byte[] getFieldBytes(int fieldLength, Queue<Byte> messageBytes) {
    byte[] fieldBytes = new byte[fieldLength];

    for (int i = 0; i < fieldLength; i++) {
      fieldBytes[i] = messageBytes.poll();
    }

    return fieldBytes;
  }

}
