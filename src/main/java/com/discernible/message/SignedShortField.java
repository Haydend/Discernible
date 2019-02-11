package com.discernible.message;

import java.nio.ByteBuffer;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class SignedShortField implements Field {

  private short value;

  public SignedShortField(short value) {
    this.value = value;
  }

  public static SignedShortField decode(Queue<Byte> messageBytes) {
    byte[] fieldBytes = ByteUtils.getFieldBytes(2, messageBytes);
    short value = ByteBuffer.wrap(fieldBytes).getShort();

    return new SignedShortField(value);
  }

  @Override
  public byte[] encode() {
    return ByteBuffer.allocate(2).putShort(value).array();
  }

}
