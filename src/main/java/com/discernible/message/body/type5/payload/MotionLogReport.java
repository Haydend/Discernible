package com.discernible.message.body.type5.payload;

import java.util.Queue;

import com.discernible.message.DateTimeField;
import com.discernible.message.UnsignedIntegerField;
import com.discernible.message.UnsignedShortField;
import com.discernible.message.body.type2.CoordinateField;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MotionLogReport extends ApplicationMessagePayload {

  public MotionLogReport() {
    super(new UnsignedIntegerField(122));
  }

  public static MotionLogReport decodePayload(Queue<Byte> fieldBytes, int fieldLength) {

    Byte recordType = fieldBytes.poll(); // 1 byte
    UnsignedShortField groupCount = UnsignedShortField.decode(fieldBytes); // 1 byte
    UnsignedShortField messageNumber = UnsignedShortField.decode(fieldBytes); // byte
    UnsignedShortField recordSize = UnsignedShortField.decode(fieldBytes); // 1 byte
    UnsignedIntegerField numberOfRecords = UnsignedIntegerField.decode(fieldBytes); // 2 bytes
    UnsignedIntegerField sampleInterval = UnsignedIntegerField.decode(fieldBytes); // 2 bytes
    DateTimeField timeDateStamp = DateTimeField.decode(fieldBytes); // 4 bytes

    for (int i = 0; i < numberOfRecords.getValue(); i++) {
      CoordinateField latitude = CoordinateField.decode(fieldBytes);
      CoordinateField longitude = CoordinateField.decode(fieldBytes);
      UnsignedIntegerField speed = UnsignedIntegerField.decode(fieldBytes);
      UnsignedShortField heading = UnsignedShortField.decode(fieldBytes);
      UnsignedShortField fixStatus = UnsignedShortField.decode(fieldBytes);
      DateTimeField timeOfFix = DateTimeField.decode(fieldBytes);

      continue;
    }


    return null;
  }

  @Override
  protected byte[] encodePayload() {
    return new byte[0];
  }

}
