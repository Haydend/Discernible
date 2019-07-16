package com.discernible.message.body;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitStatusField {

  private Status httpOtaUpdateStatus;
  private Status gpsAntennaStatus;
  private Status gpsReceiverSelfTest;
  private boolean gpsReceiverTracking;

  public enum Status {
    OK,
    ERROR
  }

}
