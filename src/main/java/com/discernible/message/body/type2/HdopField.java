package com.discernible.message.body.type2;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.message.UnsignedShortField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HdopField implements Field {

  private UnsignedShortField value;

  public HdopField(double value) {
    setValue(value);
  }

  public static HdopField decode(Queue<Byte> messageBytes) {
    UnsignedShortField shortField = UnsignedShortField.decode(messageBytes);
    return new HdopField(shortField);
  }

  @Override
  public byte[] encode() {
    return value.encode();
  }

  public void setValue(double value) {
    short movedValue = (short) Math.round(value * Math.pow(10, 1));
    this.value = new UnsignedShortField(movedValue);
  }

  public double getValue() {
    short movedValue = value.getValue();
    return (double) movedValue * Math.pow(10, -1);
  }

}
