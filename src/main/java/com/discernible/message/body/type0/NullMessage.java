package com.discernible.message.body.type0;

import com.discernible.message.body.MessageBody;

public class NullMessage extends MessageBody {

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
