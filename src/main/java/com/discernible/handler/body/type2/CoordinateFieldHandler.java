package com.discernible.handler.body.type2;

import com.discernible.handler.FieldHandler;
import com.discernible.handler.SignedIntegerFieldHandler;
import com.discernible.message.body.type2.CoordinateField;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class CoordinateFieldHandler implements FieldHandler<CoordinateField> {

  private SignedIntegerFieldHandler signedIntegerFieldHandler = new SignedIntegerFieldHandler();

  @Override
  public CoordinateField decode(JBBPBitInputStream messageBytes) {
    Integer movedValue = signedIntegerFieldHandler.decode(messageBytes);
    double value = (double) movedValue * Math.pow(10, -7); // Shift value by -7 decimal points to true value.

    return new CoordinateField(value);
  }

  @Override
  public byte[] encode(CoordinateField field) {
    double value = field.getValue();
    int movedVaue = (int) Math.round(value * Math.pow(10, 7)); // Shift value by +7 decimal points to a value that'll fit in the byte.

    return signedIntegerFieldHandler.encode(movedVaue);
  }

}
