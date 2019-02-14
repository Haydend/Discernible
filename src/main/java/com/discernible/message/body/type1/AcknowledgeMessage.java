package com.discernible.message.body.type1;

import java.util.Queue;

import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.MessageBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AcknowledgeMessage extends MessageBody {

  private TypeField typeField;
  private StatusField statusField;
  private AppVersionField appVersionField;

  public static AcknowledgeMessage decodeBody(Queue<Byte> messageBytes) {

    TypeField typeField = TypeField.decode(messageBytes);
    StatusField statusField = StatusField.decode(messageBytes);
    messageBytes.poll(); // Spare
    AppVersionField appVersionField = AppVersionField.decode(messageBytes);

    return new AcknowledgeMessage(typeField, statusField, appVersionField);
  }

  @Override
  public ServiceType getServiceType() {
    return ServiceType.RESPONSE_TO_AN_ACKNOWLEDGED_REQUEST;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.ACK_NAK_MESSAGE;
  }

  @Override
  public byte[] encodeBody() {

    byte[] messageBytes = new byte[6];

    byte[] typeFieldBytes = typeField.encode();
    System.arraycopy(typeFieldBytes, 0, messageBytes, 0, typeFieldBytes.length);

    byte[] statusFieldBytes = statusField.encode();
    System.arraycopy(statusFieldBytes, 0, messageBytes, 1, statusFieldBytes.length);

    byte[] appVersionBytes = appVersionField.encode();
    System.arraycopy(appVersionBytes, 0, messageBytes, 3, appVersionBytes.length);

    return messageBytes;
  }

}
