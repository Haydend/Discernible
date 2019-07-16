package com.discernible.handler.body.type5.payload;

import java.util.Queue;

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
  public ApplicationMessagePayload decode(Queue<Byte> fieldBytes) {

    Integer applicationMessageType = unsignedIntegerFieldHandler.decode(fieldBytes);
    Integer payloadLength = unsignedIntegerFieldHandler.decode(fieldBytes);

    final ApplicationMessagePayload applicationMessagePayload;
    switch (applicationMessageType) {
      case 131:
        applicationMessagePayload = vehicleIdReportHandler.decodePayload(fieldBytes, payloadLength);
        break;

      case 122:
        applicationMessagePayload = motionLogReportHandler.decodePayload(fieldBytes, payloadLength);
        break;

      default:
        throw new IllegalArgumentException(String.format("Type not support: %s", applicationMessageType));
    }

    return applicationMessagePayload;
  }

  @Override
  public byte[] encode(ApplicationMessagePayload message) {

    final byte[] payloadBytes;
    switch (message.getApplicationMessageType()) {
      case 131:
        payloadBytes = vehicleIdReportHandler.encodePayload((VehicleIdReport) message);
        break;

      case 122:
        payloadBytes = motionLogReportHandler.encodePayload((MotionLogReport) message);
        break;

      default:
        throw new IllegalArgumentException(String.format("Type not support: %s", message));
    }


    byte[] payloadSizeBytes = unsignedIntegerFieldHandler.encode(payloadBytes.length);
    byte[] payloadTypeBytes = unsignedIntegerFieldHandler.encode(message.getApplicationMessageType());

    byte[] messageBytes = new byte[4 + payloadBytes.length];

    System.arraycopy(payloadTypeBytes, 0, messageBytes, 0, 2);
    System.arraycopy(payloadSizeBytes, 0, messageBytes, 2, 2);
    System.arraycopy(payloadBytes, 0, messageBytes, 4, payloadBytes.length);

    return messageBytes;
  }

}
