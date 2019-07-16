package com.discernible.message.body;

import lombok.Data;

@Data
public abstract class MessageBody {

  private int sequenceNumber;

  public abstract ServiceType getServiceType();

  public abstract MessageType getMessageType();

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
