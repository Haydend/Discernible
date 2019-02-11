package com.discernible.message.body.type2;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.message.SignedIntegerField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinateField implements Field {

  private SignedIntegerField value;

  public CoordinateField(double value) {
    setValue(value);
  }

  public static CoordinateField decode(Queue<Byte> messageBytes) {
    SignedIntegerField integerField = SignedIntegerField.decode(messageBytes);
    return new CoordinateField(integerField);
  }

  @Override
  public byte[] encode() {
    return value.encode();
  }

  public void setValue(double value) {
    int movedValue = (int) Math.round(value * Math.pow(10, 7));
    this.value = new SignedIntegerField(movedValue);
  }

  public double getValue() {
    int movedValue = value.getValue();
    return (double) movedValue * Math.pow(10, -7);
  }

}
