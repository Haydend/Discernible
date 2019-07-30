package com.discernible.handler;

import java.nio.ByteBuffer;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignedIntegerFieldHandler implements FieldHandler<Integer> {

  public Integer decode(Queue<Byte> messageBytes) {
    byte[] fieldBytes = ByteUtils.getFieldBytes(4, messageBytes);
    int value = ByteBuffer.wrap(fieldBytes).getInt();

    return value;
  }

  @Override
  public void encode(Integer value, ByteOutputStream out) {
    out.write(ByteUtils.intToSignedBytes(value));
  }

}
