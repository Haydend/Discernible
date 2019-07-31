package com.discernible.handler.body.type5.payload;

import java.io.IOException;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.handler.UnsignedIntegerFieldHandler;
import com.discernible.message.body.type5.payload.ApplicationMessagePayload;
import com.discernible.message.body.type5.payload.MotionLogReport;
import com.discernible.message.body.type5.payload.VehicleIdReport;

public class ApplicationMessagePayloadHandler implements FieldHandler<ApplicationMessagePayload> {

  private UnsignedIntegerFieldHandler unsignedIntegerFieldHandler = new UnsignedIntegerFieldHandler();
  private VehicleIdReportHandler vehicleIdReportHandler = new VehicleIdReportHandler();
  private MotionLogReportHandler motionLogReportHandler = new MotionLogReportHandler();

  @Override
  public ApplicationMessagePayload decode(ByteInputStream in) {

    Integer applicationMessageType = unsignedIntegerFieldHandler.decode(in);
    Integer payloadLength = unsignedIntegerFieldHandler.decode(in);

    final ApplicationMessagePayload applicationMessagePayload;
    switch (applicationMessageType) {
      case 131:
        applicationMessagePayload = vehicleIdReportHandler.decodePayload(in, payloadLength);
        break;

      case 122:
        applicationMessagePayload = motionLogReportHandler.decodePayload(in, payloadLength);
        break;

      default:
        throw new IllegalArgumentException(String.format("Type not support: %s", applicationMessageType));
    }

    return applicationMessagePayload;
  }

  @Override
  public void encode(ApplicationMessagePayload message, ByteOutputStream output) {

    try (ByteOutputStream payloadOutput = new ByteOutputStream()) {
      switch (message.getApplicationMessageType()) {
        case 131:
          vehicleIdReportHandler.encodePayload((VehicleIdReport) message, payloadOutput);
          break;

        case 122:
          motionLogReportHandler.encodePayload((MotionLogReport) message, payloadOutput);
          break;

        default:
          throw new IllegalArgumentException(String.format("Type not support: %s", message));
      }


      byte[] payloadBytes = payloadOutput.toByteArray();

      unsignedIntegerFieldHandler.encode(message.getApplicationMessageType(), output);
      unsignedIntegerFieldHandler.encode(payloadBytes.length, output);
      output.write(payloadBytes);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
