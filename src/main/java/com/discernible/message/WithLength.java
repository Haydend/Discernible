package com.discernible.message;

public class WithLength<F extends Field> implements Field {

  private final F field;

  public WithLength(F field) {
    this.field = field;
  }

  @Override
  public byte[] encode() {
    // Get the encoded field, then get the field length that need prepending the byte array.
    byte[] fieldBytes = field.encode();
    byte fieldLength = (byte) fieldBytes.length;

    // Build a new byte array, with the field length byte first, then the field bytes;
    byte[] messageBytes = new byte[fieldBytes.length + 1];
    messageBytes[0] = fieldLength;
    System.arraycopy(fieldBytes, 0, messageBytes, 1, fieldBytes.length);

    return messageBytes;
  }

}
