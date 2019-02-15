package com.discernible.message;

import java.math.BigInteger;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class UnsignedIntegerField implements Field {

  private int value;

  public UnsignedIntegerField(int value) {
    this.value = value;
  }

  @Override
  public byte[] encode() {
    byte[] messageBytes = new byte[2];

    byte[] integerBytes = BigInteger.valueOf(value).toByteArray();
    int padding = 2 - integerBytes.length;
    System.arraycopy(integerBytes, 0, messageBytes, padding, integerBytes.length);

    return messageBytes;
  }

  public static UnsignedIntegerField decode(Queue<Byte> messageBytes) {
    byte[] valueBytes = ByteUtils.getFieldBytes(2, messageBytes);
    int value = ByteUtils.unsignedShortToInt(valueBytes);

    return new UnsignedIntegerField(value);
  }

}
