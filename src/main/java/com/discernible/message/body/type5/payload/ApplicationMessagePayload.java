package com.discernible.message.body.type5.payload;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.message.UnsignedIntegerField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class ApplicationMessagePayload implements Field {

  protected abstract byte[] encodePayload();

  private UnsignedIntegerField applicationMessageType;

  public static ApplicationMessagePayload decode(Queue<Byte> fieldBytes) {

    UnsignedIntegerField applicationMessageType = UnsignedIntegerField.decode(fieldBytes);
    UnsignedIntegerField payloadLength = UnsignedIntegerField.decode(fieldBytes);

    final ApplicationMessagePayload applicationMessagePayload;
    switch (applicationMessageType.getValue()) {
      case 131:
        applicationMessagePayload = VehicleIdReport.decodePayload(fieldBytes, payloadLength.getValue());
        break;

      case 122:
        applicationMessagePayload = MotionLogReport.decodePayload(fieldBytes, payloadLength.getValue());
        break;

      default:
        throw new IllegalArgumentException(String.format("Type not support: %s", applicationMessageType.getValue()));
    }

    return applicationMessagePayload;
  }

  @Override
  public byte[] encode() {

    byte[] payloadBytes = encodePayload();
    byte[] payloadSizeBytes = new UnsignedIntegerField(payloadBytes.length).encode();
    byte[] payloadTypeBytes = applicationMessageType.encode();

    byte[] messageBytes = new byte[4 + payloadBytes.length];

    System.arraycopy(payloadTypeBytes, 0, messageBytes, 0, 2);
    System.arraycopy(payloadSizeBytes, 0, messageBytes, 2, 2);
    System.arraycopy(payloadBytes, 0, messageBytes, 4, payloadBytes.length);

    return messageBytes;
  }

}
