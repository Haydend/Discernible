package com.discernible.handler.body.type1;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
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

  public void encode(AcknowledgeMessage acknowledgeMessage, ByteOutputStream output) {
    typeFieldHandler.encode(acknowledgeMessage.getTypeField(), output);
    statusFieldHandler.encode(acknowledgeMessage.getStatusField(), output);
    output.write(0x00);
    appVersionFieldHandler.encode(acknowledgeMessage.getAppVersionField(), output);
  }

}
