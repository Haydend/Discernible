package com.discernible.util;

import java.util.Queue;

public class ByteUtils {

  private ByteUtils() {
  };

  public static final int unsignedShortToint(byte[] b) {
    int l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
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

}
