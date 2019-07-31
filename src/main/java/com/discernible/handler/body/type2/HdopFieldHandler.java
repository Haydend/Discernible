package com.discernible.handler.body.type2;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.handler.UnsignedShortFieldHandler;
import com.discernible.message.body.type2.HdopField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HdopFieldHandler implements FieldHandler<HdopField> {

  private final UnsignedShortFieldHandler unsignedShortFieldHandler = new UnsignedShortFieldHandler();

  public HdopField decode(ByteInputStream in) {
    short movedValue = unsignedShortFieldHandler.decode(in);
    double value = (double) movedValue * Math.pow(10, -1);
    return new HdopField(value);
  }

  @Override
  public void encode(HdopField field, ByteOutputStream out) {
    double value = field.getValue();
    short movedValue = (short) Math.round(value * Math.pow(10, 1));
    unsignedShortFieldHandler.encode(movedValue, out);
  }

}
