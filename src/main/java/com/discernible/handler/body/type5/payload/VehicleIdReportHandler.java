package com.discernible.handler.body.type5.payload;

import java.util.HashMap;
import java.util.Map;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.DataListFieldHandler;
import com.discernible.message.body.type5.payload.VehicleIdReport;

public class VehicleIdReportHandler {

  private final static String VIN_KEY = "VIN";
  private final static String OBD2_PROTOCOL_ID_KEY = "PROTO";
  private final static String SUPPORTED_PARAMETERS_KEY = "PARAMS";
  private final static String SUPPORTED_INDICATORS_KEY = "INDCTRS";

  private final DataListFieldHandler dataListFieldHandler = new DataListFieldHandler();

  public VehicleIdReport decodePayload(ByteInputStream in, int fieldLength) {
    Map<String, String> data = dataListFieldHandler.decode(in);

    String vin = data.get(VIN_KEY);
    Integer obd2ProtocolId = Integer.parseInt(data.get(OBD2_PROTOCOL_ID_KEY));
    String supportedParameters = data.get(SUPPORTED_PARAMETERS_KEY);
    String supportedIndicators = data.get(SUPPORTED_INDICATORS_KEY);

    return new VehicleIdReport(vin, obd2ProtocolId, supportedParameters, supportedIndicators);
  }

  public void encodePayload(VehicleIdReport vehicleIdReport, ByteOutputStream output) {

    Map<String, String> payload = new HashMap<>();
    payload.put(VIN_KEY, vehicleIdReport.getVin());
    payload.put(OBD2_PROTOCOL_ID_KEY, new Integer(vehicleIdReport.getObd2ProtocolId()).toString());
    payload.put(SUPPORTED_PARAMETERS_KEY, vehicleIdReport.getSupportedParameters());
    payload.put(SUPPORTED_INDICATORS_KEY, vehicleIdReport.getSupportedIndicators());

    dataListFieldHandler.encode(payload, output);
  }

}
