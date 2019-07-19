package com.discernible.handler;

import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.Data;

@Data
public class UnsignedShortFieldHandler implements FieldHandler<Short> {

  @Override
  public byte[] encode(Short value) {
    return new byte[] {value.byteValue()};
  }

  public Short decode(JBBPBitInputStream messageBytes) {

    byte fieldByte = ByteUtils.getByte(messageBytes);
    short value = (short) Byte.toUnsignedInt(fieldByte);

    return value;
  }

}
