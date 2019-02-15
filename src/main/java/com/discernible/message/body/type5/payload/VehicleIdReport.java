package com.discernible.message.body.type5.payload;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import com.discernible.message.DataListField;
import com.discernible.message.UnsignedIntegerField;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VehicleIdReport extends ApplicationMessagePayload {

  private final static String VIN_KEY = "VIN";
  private final static String OBD2_PROTOCOL_ID_KEY = "PROTO";
  private final static String SUPPORTED_PARAMETERS_KEY = "PARAMS";
  private final static String SUPPORTED_INDICATORS_KEY = "INDCTRS";

  private String vin;
  private int obd2ProtocolId;
  private String supportedParameters;
  private String supportedIndicators;

  public VehicleIdReport(String vin, int obd2ProtocolId, String supportedParameters,
      String supportedIndicators) {
    super(new UnsignedIntegerField(131));

    this.vin = vin;
    this.obd2ProtocolId = obd2ProtocolId;
    this.supportedParameters = supportedParameters;
    this.supportedIndicators = supportedIndicators;
  }

  public static VehicleIdReport decodePayload(Queue<Byte> fieldBytes, int fieldLength) {
    DataListField payload = DataListField.decode(fieldBytes);

    Map<String, String> data = payload.getData();

    String vin = data.get(VIN_KEY);
    Integer obd2ProtocolId = Integer.parseInt(data.get(OBD2_PROTOCOL_ID_KEY));
    String supportedParameters = data.get(SUPPORTED_PARAMETERS_KEY);
    String supportedIndicators = data.get(SUPPORTED_INDICATORS_KEY);

    return new VehicleIdReport(vin, obd2ProtocolId, supportedParameters, supportedIndicators);
  }

  @Override
  protected byte[] encodePayload() {

    Map<String, String> payload = new HashMap<>();
    payload.put(VIN_KEY, vin);
    payload.put(OBD2_PROTOCOL_ID_KEY, new Integer(obd2ProtocolId).toString());
    payload.put(SUPPORTED_PARAMETERS_KEY, supportedParameters);
    payload.put(SUPPORTED_INDICATORS_KEY, supportedIndicators);

    DataListField dataListField = new DataListField(payload);
    return dataListField.encode();
  }

}
