package com.discernible.handler;

import java.nio.ByteBuffer;

import lombok.Data;

@Data
public class SignedShortFieldHandler implements FieldHandler<Short> {

  public Short decode(ByteInputStream in) {
    byte[] fieldBytes = in.read(2);
    short value = ByteBuffer.wrap(fieldBytes).getShort();

    return value;
  }

  @Override
  public void encode(Short value, ByteOutputStream out) {
    out.write(ByteBuffer.allocate(2).putShort(value).array());
  }

}
