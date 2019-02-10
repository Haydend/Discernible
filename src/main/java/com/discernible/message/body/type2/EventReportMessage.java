package com.discernible.message.body.type2;

import java.util.Queue;

import com.discernible.message.body.MessageBody;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventReportMessage extends MessageBody {

  private ServiceType serviceType;

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
