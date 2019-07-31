package com.discernible.handler;

import java.math.BigInteger;

import lombok.Data;

@Data
public class UnsignedLongFieldHandler implements FieldHandler<Long> {

  @Override
  public void encode(Long value, ByteOutputStream out) {
    byte[] messageBytes = new byte[4];
    byte[] randomKeyBytes = BigInteger.valueOf(value).toByteArray();
    int padding = 4 - randomKeyBytes.length;
    System.arraycopy(randomKeyBytes, 0, messageBytes, padding, randomKeyBytes.length);
    out.write(messageBytes);
  }

  public Long decode(ByteInputStream in) {
    byte[] valueBytes = in.read(4);
    long value = unsignedIntToLong(valueBytes);

    return value;
  }

  private static final long unsignedIntToLong(byte[] b) {
    long l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
    l <<= 8;
    l |= b[2] & 0xFF;
    l <<= 8;
    l |= b[3] & 0xFF;
    return l;
  }

}
