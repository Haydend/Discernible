package com.discernible.message.body.type2;

import java.util.Queue;

import com.discernible.message.DateTimeField;
import com.discernible.message.SignedIntegerField;
import com.discernible.message.SignedShortField;
import com.discernible.message.UnsignedIntegerField;
import com.discernible.message.UnsignedShortField;
import com.discernible.message.body.MessageBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventReportMessage extends MessageBody {

  private ServiceType serviceType;
  private DateTimeField updateTime;
  private DateTimeField timeOfFix;
  private CoordinateField latitude;
  private CoordinateField longitude;
  private SignedIntegerField altitude;
  private SignedIntegerField speed;
  private UnsignedIntegerField heading;
  private UnsignedShortField satellitesCount;
  private UnsignedIntegerField carrierId;
  private SignedShortField rssi;

  public static EventReportMessage decodeBody(Queue<Byte> messageBytes) {

    return null;
  }

  @Override
  public ServiceType getServiceType() {
    return serviceType;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.EVENT_REPORT_MESSAGE;
  }

  @Override
  public byte[] encodeBody() {
    return new byte[0];
  }

}
