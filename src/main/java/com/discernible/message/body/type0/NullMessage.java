package com.discernible.message.body.type0;

import java.util.Queue;

import com.discernible.message.body.MessageBody;

public class NullMessage extends MessageBody {

  public static NullMessage decodeBody(Queue<Byte> messageBytes) {
    return new NullMessage();
  }

  @Override
  public ServiceType getServiceType() {
    return ServiceType.UNACKNOWLEDGED_REQUEST;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.NULL_MESSAGE;
  }

  @Override
  public byte[] encodeBody() {
    return new byte[0];
  }

}
