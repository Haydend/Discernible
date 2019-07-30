package com.discernible.handler;

import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class UnsignedIntegerFieldHandler implements FieldHandler<Integer> {

  @Override
  public void encode(Integer value, ByteOutputStream out) {
    out.writeUnsignedShort(value);
  }

  public Integer decode(Queue<Byte> messageBytes) {
    byte[] valueBytes = ByteUtils.getFieldBytes(2, messageBytes);
    int value = ByteUtils.unsignedShortToInt(valueBytes);

    return value;
  }

}
