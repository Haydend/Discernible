package com.discernible.handler;

import java.util.Queue;

import lombok.Data;

@Data
public class UnsignedShortFieldHandler implements FieldHandler<Short> {

  @Override
  public byte[] encode(Short value) {
    return new byte[] {value.byteValue()};
  }

  public Short decode(Queue<Byte> messageBytes) {

    byte fieldByte = messageBytes.poll();
    short value = (short) Byte.toUnsignedInt(fieldByte);

    return value;
  }

}
