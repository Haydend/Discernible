package com.discernible.handler;

import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class UnsignedIntegerFieldHandler implements FieldHandler<Integer> {

  @Override
  public void encode(Integer value, ByteOutputStream out) {
    out.writeUnsignedShort(value);
  }

  public Integer decode(ByteInputStream in) {
    byte[] valueBytes = in.read(2);
    int value = ByteUtils.unsignedShortToInt(valueBytes);

    return value;
  }

}
