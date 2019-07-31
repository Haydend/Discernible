package com.discernible.handler;

import java.math.BigInteger;

import lombok.Data;

@Data
public class UnsignedIntegerFieldHandler implements FieldHandler<Integer> {

  @Override
  public void encode(Integer value, ByteOutputStream out) {
    byte[] messageBytes = new byte[2];
    byte[] randomKeyBytes = BigInteger.valueOf(value).toByteArray();
    int padding = 2 - randomKeyBytes.length;
    System.arraycopy(randomKeyBytes, 0, messageBytes, padding, randomKeyBytes.length);
    out.write(messageBytes);
  }

  public Integer decode(ByteInputStream in) {
    byte[] valueBytes = in.read(2);
    int value = unsignedShortToInt(valueBytes);

    return value;
  }

  private static final int unsignedShortToInt(byte[] b) {
    int l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
    return l;
  }

}
