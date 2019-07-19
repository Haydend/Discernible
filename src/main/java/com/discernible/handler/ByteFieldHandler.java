package com.discernible.handler;

import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class ByteFieldHandler implements FieldHandler<byte[]> {

  public byte[] decode(JBBPBitInputStream messageBytes) {

    int fieldLength = ByteUtils.getFieldLength(messageBytes);

    byte[] fieldBytes = ByteUtils.getFieldBytes(fieldLength, messageBytes);

    return fieldBytes;
  }

  @Override
  public byte[] encode(byte[] field) {
    return ByteUtils.prependFieldLength(field);
  }

}
