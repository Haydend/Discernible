package com.discernible.handler.body.type1;

import com.discernible.handler.ByteInputStream;
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

  public AcknowledgeMessage decode(ByteInputStream in) {

    TypeField typeField = typeFieldHandler.decode(in);
    StatusField statusField = statusFieldHandler.decode(in);
    in.read(); // Spare
    AppVersionField appVersionField = appVersionFieldHandler.decode(in);

    return new AcknowledgeMessage(typeField, statusField, appVersionField);
  }

  public void encode(AcknowledgeMessage acknowledgeMessage, ByteOutputStream output) {
    typeFieldHandler.encode(acknowledgeMessage.getTypeField(), output);
    statusFieldHandler.encode(acknowledgeMessage.getStatusField(), output);
    output.write(0x00);
    appVersionFieldHandler.encode(acknowledgeMessage.getAppVersionField(), output);
  }

}
