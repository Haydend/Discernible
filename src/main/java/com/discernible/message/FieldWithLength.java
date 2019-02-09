package com.discernible.message;

public abstract class FieldWithLength implements Field {

  protected abstract byte[] encodeField();

  @Override
  public byte[] encode() {

    // Get the encoded field, then get the field length that need prepending the byte array.
    byte[] fieldBytes = encodeField();
    byte fieldLength = (byte) fieldBytes.length;

    // Build a new byte array, with the field length byte first, then the field bytes;
    byte[] messageBytes = new byte[fieldBytes.length + 1];
    messageBytes[0] = fieldLength;
    System.arraycopy(fieldBytes, 0, messageBytes, 1, fieldBytes.length);

    return messageBytes;
  }

}
