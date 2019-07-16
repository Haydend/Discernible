package com.discernible.handler.body;

import java.math.BigInteger;
import java.util.Queue;

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

  public byte[] encodeHeader(Message message) {

    byte[] headerBytes = new byte[4];

    headerBytes[0] = (byte) message.getServiceType().ordinal();
    headerBytes[1] = (byte) message.getMessageType().ordinal();

    byte[] sequenceNumberBytes = BigInteger.valueOf(message.getSequenceNumber()).toByteArray();
    int padding = 2 - sequenceNumberBytes.length;
    System.arraycopy(sequenceNumberBytes, 0, headerBytes, 2 + padding, sequenceNumberBytes.length);



    return headerBytes;
  }

  public byte[] encodeBody(Message message, boolean sentFromLmu) {
    byte[] messageBytes;
    switch (message.getMessageType()) {
      case NULL_MESSAGE:
        messageBytes = nullMessageHandler.encodeBody((NullMessage) message);
        break;

      case ACK_NAK_MESSAGE:
        messageBytes = acknowledgeMessageHandler.encode((AcknowledgeMessage) message);
        break;

      case EVENT_REPORT_MESSAGE:
        messageBytes = eventReportMessageHandler.encodeBody((EventReportMessage) message);
        break;

      case ID_REPORT_MESSAGE:
        messageBytes = idReportMessageHandler.encodeBody((IdReportMessage) message);
        break;

      case APPLICATION_DATA_MESSAGE:
        messageBytes = applicationMessageHandler.encodeBody((ApplicationMessage) message, sentFromLmu);
        break;

      default:
        throw new IllegalStateException("Message Type not supported");
    }

    return messageBytes;
  }

  public byte[] encode(Message message, boolean sendFromLmu) {

    byte[] optionHeaderBytes = optionsHeaderFieldHandler.encode(message.getOptionHeader());
    byte[] headerBytes = encodeHeader(message);
    byte[] bodyBytes = encodeBody(message, sendFromLmu);

    byte[] messageBytes = new byte[optionHeaderBytes.length + headerBytes.length + bodyBytes.length];

    System.arraycopy(optionHeaderBytes, 0, messageBytes, 0, optionHeaderBytes.length);
    System.arraycopy(headerBytes, 0, messageBytes, optionHeaderBytes.length, headerBytes.length);
    System.arraycopy(bodyBytes, 0, messageBytes, optionHeaderBytes.length + headerBytes.length, bodyBytes.length);

    return messageBytes;
  }

}
