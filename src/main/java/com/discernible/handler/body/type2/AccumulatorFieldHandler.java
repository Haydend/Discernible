package com.discernible.handler.body.type2;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type2.AccumulatorField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccumulatorFieldHandler implements FieldHandler<AccumulatorField> {

  @Override
  public AccumulatorField decode(ByteInputStream in) {
    byte[] bytes = in.read(4);
    return new AccumulatorField(bytes);
  }

  @Override
  public void encode(AccumulatorField field, ByteOutputStream out) {
    out.write(field.getBytes());
  }

}
