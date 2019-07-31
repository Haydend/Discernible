package com.discernible.handler;

import java.nio.ByteBuffer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignedIntegerFieldHandler implements FieldHandler<Integer> {

  public Integer decode(ByteInputStream in) {
    byte[] fieldBytes = in.read(4);
    int value = ByteBuffer.wrap(fieldBytes).getInt();

    return value;
  }

  @Override
  public void encode(Integer value, ByteOutputStream out) {
    out.write(intToSignedBytes(value));
  }

  private static final byte[] intToSignedBytes(int value) {
    return new byte[] {(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value};
  }

}
