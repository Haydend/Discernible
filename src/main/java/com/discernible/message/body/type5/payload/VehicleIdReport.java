package com.discernible.message.body.type5.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VehicleIdReport extends ApplicationMessagePayload {

  private String vin;
  private int obd2ProtocolId;
  private String supportedParameters;
  private String supportedIndicators;

  public VehicleIdReport(String vin, int obd2ProtocolId, String supportedParameters,
      String supportedIndicators) {
    super(131);

    this.vin = vin;
    this.obd2ProtocolId = obd2ProtocolId;
    this.supportedParameters = supportedParameters;
    this.supportedIndicators = supportedIndicators;
  }

}
