package com.discernible.message.body;

import java.util.Queue;

import com.discernible.message.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitStatusField implements Field {

  private Status httpOtaUpdateStatus;
  private Status gpsAntennaStatus;
  private Status gpsReceiverSelfTest;
  private boolean gpsReceiverTracking;

  public static UnitStatusField decode(Queue<Byte> messageBytes) {

    byte fieldByte = messageBytes.poll();

    Status httpOtaUpdateStatus = (fieldByte & 0b00000001) != 0 ? Status.OK : Status.ERROR;
    Status gpsAntennaStatus = (fieldByte & 0b00000010) != 0 ? Status.OK : Status.ERROR;
    Status gpsReceiverSelfTest = (fieldByte & 0b00000100) != 0 ? Status.OK : Status.ERROR;
    boolean gpsReceiverTracking = (fieldByte & 0b00001000) != 0;

    return new UnitStatusField(httpOtaUpdateStatus, gpsAntennaStatus, gpsReceiverSelfTest, gpsReceiverTracking);
  }

  @Override
  public byte[] encode() {
    byte fieldByte = (byte) 0b00000000;

    if (Status.OK.equals(httpOtaUpdateStatus)) {
      fieldByte = (byte) (fieldByte | 0b00000001);
    }

    if (Status.OK.equals(gpsAntennaStatus)) {
      fieldByte = (byte) (fieldByte | 0b00000010);
    }

    if (Status.OK.equals(gpsReceiverSelfTest)) {
      fieldByte = (byte) (fieldByte | 0b00000100);
    }

    if (gpsReceiverTracking) {
      fieldByte = (byte) (fieldByte | 0b00001000);
    }

    return new byte[] {fieldByte};
  }

  public enum Status {
    OK,
    ERROR
  }

}
