package com.discernible.handler.body.type2;

import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.handler.UnsignedShortFieldHandler;
import com.discernible.message.body.type2.HdopField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HdopFieldHandler implements FieldHandler<HdopField> {

  private final UnsignedShortFieldHandler unsignedShortFieldHandler = new UnsignedShortFieldHandler();

  public HdopField decode(Queue<Byte> messageBytes) {
    short movedValue = unsignedShortFieldHandler.decode(messageBytes);
    double value = (double) movedValue * Math.pow(10, -1);
    return new HdopField(value);
  }

  @Override
  public byte[] encode(HdopField field) {
    double value = field.getValue();
    short movedValue = (short) Math.round(value * Math.pow(10, 1));
    return unsignedShortFieldHandler.encode(movedValue);
  }

}
