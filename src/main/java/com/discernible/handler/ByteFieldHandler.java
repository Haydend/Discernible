package com.discernible.handler;

public class ByteFieldHandler implements FieldHandler<byte[]> {

  public byte[] decode(ByteInputStream in) {

    int fieldLength = in.read();

    byte[] fieldBytes = in.read(fieldLength);

    return fieldBytes;
  }

  @Override
  public void encode(byte[] field, ByteOutputStream out) {
    out.write(field.length);
    out.write(field);
  }

}
