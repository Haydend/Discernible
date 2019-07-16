package com.discernible.message.body.type1;

import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AcknowledgeMessage extends Message {

  private TypeField typeField;
  private StatusField statusField;
  private AppVersionField appVersionField;

  @Override
  public ServiceType getServiceType() {
    return ServiceType.RESPONSE_TO_AN_ACKNOWLEDGED_REQUEST;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.ACK_NAK_MESSAGE;
  }

}
