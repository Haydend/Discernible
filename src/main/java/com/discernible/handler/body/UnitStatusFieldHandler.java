package com.discernible.handler.body;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.UnitStatusField.Status;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitStatusFieldHandler implements FieldHandler<UnitStatusField> {

  public UnitStatusField decode(Queue<Byte> messageBytes) {

    byte fieldByte = messageBytes.poll();

    Status httpOtaUpdateStatus = (fieldByte & 0b00000001) != 0 ? Status.OK : Status.ERROR;
    Status gpsAntennaStatus = (fieldByte & 0b00000010) != 0 ? Status.OK : Status.ERROR;
    Status gpsReceiverSelfTest = (fieldByte & 0b00000100) != 0 ? Status.OK : Status.ERROR;
    boolean gpsReceiverTracking = (fieldByte & 0b00001000) != 0;

    return new UnitStatusField(httpOtaUpdateStatus, gpsAntennaStatus, gpsReceiverSelfTest, gpsReceiverTracking);
  }

  @Override
  public void encode(UnitStatusField field, ByteOutputStream output) {
    byte fieldByte = (byte) 0b00000000;

    if (Status.OK.equals(field.getHttpOtaUpdateStatus())) {
      fieldByte = (byte) (fieldByte | 0b00000001);
    }

    if (Status.OK.equals(field.getGpsAntennaStatus())) {
      fieldByte = (byte) (fieldByte | 0b00000010);
    }

    if (Status.OK.equals(field.getGpsReceiverSelfTest())) {
      fieldByte = (byte) (fieldByte | 0b00000100);
    }

    if (field.isGpsReceiverTracking()) {
      fieldByte = (byte) (fieldByte | 0b00001000);
    }

    output.write(fieldByte);
  }

}
