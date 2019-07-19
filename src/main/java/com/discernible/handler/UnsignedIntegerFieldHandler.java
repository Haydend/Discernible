package com.discernible.handler;

import java.math.BigInteger;

import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.Data;

@Data
public class UnsignedIntegerFieldHandler implements FieldHandler<Integer> {

  @Override
  public byte[] encode(Integer value) {
    byte[] messageBytes = new byte[2];

    byte[] integerBytes = BigInteger.valueOf(value).toByteArray();
    int padding = 2 - integerBytes.length;
    System.arraycopy(integerBytes, 0, messageBytes, padding, integerBytes.length);

    return messageBytes;
  }

  public Integer decode(JBBPBitInputStream messageBytes) {
    byte[] valueBytes = ByteUtils.getFieldBytes(2, messageBytes);
    int value = ByteUtils.unsignedShortToInt(valueBytes);

    return value;
  }

}
