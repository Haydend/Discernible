package com.discernible.message;

import java.nio.ByteBuffer;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignedIntegerField implements Field {

  private int value;

  public static SignedIntegerField decode(Queue<Byte> messageBytes) {
    byte[] fieldBytes = ByteUtils.getFieldBytes(4, messageBytes);
    int value = ByteBuffer.wrap(fieldBytes).getInt();

    return new SignedIntegerField(value);
  }

  @Override
  public byte[] encode() {
    return ByteUtils.intToSignedBytes(value);
  }

}
