package com.discernible.handler;

import lombok.Data;

@Data
public class UnsignedShortFieldHandler implements FieldHandler<Short> {

  @Override
  public void encode(Short value, ByteOutputStream out) {
    out.write(value.byteValue());
  }

  public Short decode(ByteInputStream in) {

    byte fieldByte = (byte) in.read();
    short value = (short) Byte.toUnsignedInt(fieldByte);

    return value;
  }

}
