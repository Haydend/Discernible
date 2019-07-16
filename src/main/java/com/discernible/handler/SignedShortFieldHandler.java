package com.discernible.handler;

import java.nio.ByteBuffer;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class SignedShortFieldHandler implements FieldHandler<Short> {

  public Short decode(Queue<Byte> messageBytes) {
    byte[] fieldBytes = ByteUtils.getFieldBytes(2, messageBytes);
    short value = ByteBuffer.wrap(fieldBytes).getShort();

    return value;
  }

  @Override
  public byte[] encode(Short value) {
    return ByteBuffer.allocate(2).putShort(value).array();
  }

}
