package com.discernible.handler;

import java.nio.ByteBuffer;

import com.discernible.util.ByteUtils;

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
    out.write(ByteUtils.intToSignedBytes(value));
  }

}
