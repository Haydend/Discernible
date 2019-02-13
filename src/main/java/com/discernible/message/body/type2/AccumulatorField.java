package com.discernible.message.body.type2;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccumulatorField implements Field {

  private byte[] bytes;

  public static AccumulatorField decode(Queue<Byte> messageQueue) {
    byte[] bytes = ByteUtils.getFieldBytes(4, messageQueue);
    return new AccumulatorField(bytes);
  }

  @Override
  public byte[] encode() {
    return bytes;
  }

}
