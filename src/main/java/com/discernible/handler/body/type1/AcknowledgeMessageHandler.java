package com.discernible.handler.body.type1;

import java.util.Queue;

import com.discernible.handler.body.AppVersionFieldHandler;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.type1.AcknowledgeMessage;
import com.discernible.message.body.type1.StatusField;
import com.discernible.message.body.type1.TypeField;

public class AcknowledgeMessageHandler {

  private final TypeFieldHandler typeFieldHandler = new TypeFieldHandler();
  private StatusFieldHandler statusFieldHandler = new StatusFieldHandler();
  private AppVersionFieldHandler appVersionFieldHandler = new AppVersionFieldHandler();

  public AcknowledgeMessage decode(Queue<Byte> messageBytes) {

    TypeField typeField = typeFieldHandler.decode(messageBytes);
    StatusField statusField = statusFieldHandler.decode(messageBytes);
    messageBytes.poll(); // Spare
    AppVersionField appVersionField = appVersionFieldHandler.decode(messageBytes);

    return new AcknowledgeMessage(typeField, statusField, appVersionField);
  }

  public byte[] encode(AcknowledgeMessage acknowledgeMessage) {

    byte[] messageBytes = new byte[6];

    byte[] typeFieldBytes = typeFieldHandler.encode(acknowledgeMessage.getTypeField());
    System.arraycopy(typeFieldBytes, 0, messageBytes, 0, typeFieldBytes.length);

    byte[] statusFieldBytes = statusFieldHandler.encode(acknowledgeMessage.getStatusField());
    System.arraycopy(statusFieldBytes, 0, messageBytes, 1, statusFieldBytes.length);

    byte[] appVersionBytes = appVersionFieldHandler.encode(acknowledgeMessage.getAppVersionField());
    System.arraycopy(appVersionBytes, 0, messageBytes, 3, appVersionBytes.length);

    return messageBytes;
  }

}
