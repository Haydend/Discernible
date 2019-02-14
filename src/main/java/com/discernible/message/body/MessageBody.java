package com.discernible.message.body;

import java.math.BigInteger;
import java.util.Queue;

import com.discernible.message.body.type0.NullMessage;
import com.discernible.message.body.type1.AcknowledgeMessage;
import com.discernible.message.body.type2.EventReportMessage;
import com.discernible.message.body.type3.IdReportMessage;
import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public abstract class MessageBody {

  private int sequenceNumber;

  public static MessageBody decode(Queue<Byte> messageBytes) {

    ServiceType serviceType = ServiceType.values()[messageBytes.poll()];
    MessageType messageType = MessageType.values()[messageBytes.poll()];
    int sequenceNumber = ByteUtils.unsignedShortToInt(ByteUtils.getFieldBytes(2, messageBytes));

    final MessageBody messageBody;
    switch (messageType) {
      case NULL_MESSAGE:
        messageBody = NullMessage.decodeBody(messageBytes);
        break;

      case ACK_NAK_MESSAGE:
        messageBody = AcknowledgeMessage.decodeBody(messageBytes);
        break;

      case EVENT_REPORT_MESSAGE:
        messageBody = EventReportMessage.decodeBody(messageBytes, serviceType);
        break;

      case ID_REPORT_MESSAGE:
        messageBody = IdReportMessage.decodeBody(messageBytes, serviceType);
        break;

      default:
        throw new IllegalStateException("Message Type not supported");
    }

    messageBody.setSequenceNumber(sequenceNumber);
    return messageBody;
  }

  public abstract ServiceType getServiceType();

  public abstract MessageType getMessageType();

  public abstract byte[] encodeBody();

  public byte[] encodeMessageHeader() {

    byte[] headerBytes = new byte[4];

    headerBytes[0] = (byte) getServiceType().ordinal();
    headerBytes[1] = (byte) getMessageType().ordinal();

    byte[] sequenceNumberBytes = BigInteger.valueOf(sequenceNumber).toByteArray();
    int padding = 2 - sequenceNumberBytes.length;
    System.arraycopy(sequenceNumberBytes, 0, headerBytes, 2 + padding, sequenceNumberBytes.length);

    return headerBytes;
  }

  public enum ServiceType {
    UNACKNOWLEDGED_REQUEST,
    ACKNOWLEDGED_REQUEST,
    RESPONSE_TO_AN_ACKNOWLEDGED_REQUEST;
  }

  public enum MessageType {
    NULL_MESSAGE,
    ACK_NAK_MESSAGE,
    EVENT_REPORT_MESSAGE,
    ID_REPORT_MESSAGE,
    USER_DATA_MESSAGE,
    APPLICATION_DATA_MESSAGE,
    CONFIGURATION_PARAMETER_MESSAGE,
    UNIT_REQUEST_MESSAGE,
    LOCATE_REPORT_MESSAGE,
    USER_DATA_WITH_ACCUMULATORS_MESSAGE,
    MINI_EVENT_REPORT_MESSAGE,
    MINI_USER_DATA_MESSAGE,
    MINI_APPLICATION_MESSAGE,
    DEVICE_VERSION_MESSAGE,
    APPLICATION_MESSAGE_WITH_ACCUMULATORS;
  }

}
