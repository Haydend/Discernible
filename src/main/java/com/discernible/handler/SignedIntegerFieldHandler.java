package com.discernible.handler;

import java.nio.ByteBuffer;

import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignedIntegerFieldHandler implements FieldHandler<Integer> {

  public Integer decode(JBBPBitInputStream messageBytes) {
    byte[] fieldBytes = ByteUtils.getFieldBytes(4, messageBytes);
    int value = ByteBuffer.wrap(fieldBytes).getInt();

    return value;
  }

  @Override
  public byte[] encode(Integer value) {
    return ByteUtils.intToSignedBytes(value);
  }

}
