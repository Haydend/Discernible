package com.discernible.message.body.type0;

import com.discernible.message.body.Message;

public class NullMessage extends Message {

  @Override
  public ServiceType getServiceType() {
    return ServiceType.UNACKNOWLEDGED_REQUEST;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.NULL_MESSAGE;
  }

}
