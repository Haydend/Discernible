package com.discernible.handler.body.type2;

import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type2.AccumulatorField;
import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccumulatorFieldHandler implements FieldHandler<AccumulatorField> {

  @Override
  public AccumulatorField decode(Queue<Byte> messageQueue) {
    byte[] bytes = ByteUtils.getFieldBytes(4, messageQueue);
    return new AccumulatorField(bytes);
  }

  @Override
  public byte[] encode(AccumulatorField field) {
    return field.getBytes();
  }

}
