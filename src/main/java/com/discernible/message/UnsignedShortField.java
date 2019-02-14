package com.discernible.message;

import java.util.Queue;

import lombok.Data;

@Data
public class UnsignedShortField implements Field {

  private short value;

  public UnsignedShortField(short value) {
    this.value = value;
  }

  @Override
  public byte[] encode() {
    return new byte[] {(byte) value};
  }

  public static UnsignedShortField decode(Queue<Byte> messageBytes) {

    byte fieldByte = messageBytes.poll();
    short value = (short) Byte.toUnsignedInt(fieldByte);

    return new UnsignedShortField(value);
  }

}
