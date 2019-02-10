package com.discernible.message;

import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class ByteField implements Field {

  private final byte[] field;

  public static ByteField decode(Queue<Byte> messageBytes) {

    int fieldLength = ByteUtils.getFieldLength(messageBytes);

    byte[] fieldBytes = ByteUtils.getFieldBytes(fieldLength, messageBytes);

    return new ByteField(fieldBytes);
  }

  @Override
  public byte[] encode() {
    return ByteUtils.prependFieldLength(field);
  }

}
