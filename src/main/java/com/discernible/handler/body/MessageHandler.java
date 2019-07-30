package com.discernible.handler.body;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type0.NullMessageHandler;
import com.discernible.handler.body.type1.AcknowledgeMessageHandler;
import com.discernible.handler.body.type2.EventReportMessageHandler;
import com.discernible.handler.body.type3.IdReportMessageHandler;
import com.discernible.handler.body.type5.ApplicationMessageHandler;
import com.discernible.handler.header.options.OptionsHeaderFieldHandler;
import com.discernible.message.body.Message;
import com.discernible.message.body.Message.MessageType;
import com.discernible.message.body.Message.ServiceType;
import com.discernible.message.body.type0.NullMessage;
import com.discernible.message.body.type1.AcknowledgeMessage;
import com.discernible.message.body.type2.EventReportMessage;
import com.discernible.message.body.type3.IdReportMessage;
import com.discernible.message.body.type5.ApplicationMessage;
import com.discernible.message.header.options.OptionsHeader;
import com.discernible.util.ByteUtils;

public class MessageHandler {

  private final NullMessageHandler nullMessageHandler = new NullMessageHandler();
  private final AcknowledgeMessageHandler acknowledgeMessageHandler = new AcknowledgeMessageHandler();
  private final EventReportMessageHandler eventReportMessageHandler = new EventReportMessageHandler();
  private final IdReportMessageHandler idReportMessageHandler = new IdReportMessageHandler();
  private final ApplicationMessageHandler applicationMessageHandler = new ApplicationMessageHandler();
  private final OptionsHeaderFieldHandler optionsHeaderFieldHandler = new OptionsHeaderFieldHandler();

  public Message decode(Queue<Byte> messageBytes, boolean sentFromLmu) {

    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(messageBytes);

    ServiceType serviceType = ServiceType.values()[messageBytes.poll()];
    MessageType messageType = MessageType.values()[messageBytes.poll()];
    int sequenceNumber = ByteUtils.unsignedShortToInt(ByteUtils.getFieldBytes(2, messageBytes));

    final Message messageBody;
    switch (messageType) {
      case NULL_MESSAGE:
        messageBody = nullMessageHandler.decodeBody(messageBytes);
        break;

      case ACK_NAK_MESSAGE:
        messageBody = acknowledgeMessageHandler.decode(messageBytes);
        break;

      case EVENT_REPORT_MESSAGE:
        messageBody = eventReportMessageHandler.decodeBody(messageBytes, serviceType);
        break;

      case ID_REPORT_MESSAGE:
        messageBody = idReportMessageHandler.decodeBody(messageBytes, serviceType);
        break;

      case APPLICATION_DATA_MESSAGE:
        messageBody = applicationMessageHandler.decodeBody(messageBytes, serviceType, sentFromLmu);
        break;

      default:
        throw new IllegalStateException("Message Type not supported");
    }

    messageBody.setSequenceNumber(sequenceNumber);
    messageBody.setOptionHeader(optionsHeader);
    return messageBody;
  }

  public void encodeHeader(Message message, ByteOutputStream out) {
    out.write(message.getServiceType().ordinal());
    out.write(message.getMessageType().ordinal());
    out.writeUnsignedShort(message.getSequenceNumber());
  }

  public void encodeBody(Message message, boolean sentFromLmu, ByteOutputStream out) {
    switch (message.getMessageType()) {
      case NULL_MESSAGE:
        nullMessageHandler.encodeBody((NullMessage) message, out);
        break;

      case ACK_NAK_MESSAGE:
        acknowledgeMessageHandler.encode((AcknowledgeMessage) message, out);
        break;

      case EVENT_REPORT_MESSAGE:
        eventReportMessageHandler.encodeBody((EventReportMessage) message, out);
        break;

      case ID_REPORT_MESSAGE:
        idReportMessageHandler.encodeBody((IdReportMessage) message, out);
        break;

      case APPLICATION_DATA_MESSAGE:
        applicationMessageHandler.encodeBody((ApplicationMessage) message, sentFromLmu, out);
        break;

      default:
        throw new IllegalStateException("Message Type not supported");
    }
  }

  public byte[] encode(Message message, boolean sendFromLmu) {

    ByteOutputStream out = new ByteOutputStream();

    optionsHeaderFieldHandler.encode(message.getOptionHeader(), out);
    encodeHeader(message, out);
    encodeBody(message, sendFromLmu, out);

    return out.toByteArray();
  }

}
