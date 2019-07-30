package com.discernible.handler;

import java.util.Queue;

import com.discernible.util.ByteUtils;

public class ByteFieldHandler implements FieldHandler<byte[]> {

  public byte[] decode(Queue<Byte> messageBytes) {

    int fieldLength = ByteUtils.getFieldLength(messageBytes);

    byte[] fieldBytes = ByteUtils.getFieldBytes(fieldLength, messageBytes);

    return fieldBytes;
  }

  @Override
  public void encode(byte[] field, ByteOutputStream out) {
    out.write(field.length);
    out.write(field);
  }

}
