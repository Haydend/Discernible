package com.discernible.handler.body.type2;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.handler.SignedIntegerFieldHandler;
import com.discernible.message.body.type2.CoordinateField;

public class CoordinateFieldHandler implements FieldHandler<CoordinateField> {

  private SignedIntegerFieldHandler signedIntegerFieldHandler = new SignedIntegerFieldHandler();

  @Override
  public CoordinateField decode(ByteInputStream in) {
    Integer movedValue = signedIntegerFieldHandler.decode(in);
    double value = (double) movedValue * Math.pow(10, -7); // Shift value by -7 decimal points to true value.

    return new CoordinateField(value);
  }

  @Override
  public void encode(CoordinateField field, ByteOutputStream out) {
    double value = field.getValue();
    int movedVaue = (int) Math.round(value * Math.pow(10, 7)); // Shift value by +7 decimal points to a value that'll fit in the byte.

    signedIntegerFieldHandler.encode(movedVaue, out);
  }

}
