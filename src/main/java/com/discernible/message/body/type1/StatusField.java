package com.discernible.message.body.type1;

import java.util.Queue;

import com.discernible.message.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusField implements Field {

  private Status status;

  public static StatusField decode(Queue<Byte> messageBytes) {

    byte statusByte = messageBytes.poll();
    Status status = Status.values()[(int) statusByte];

    return new StatusField(status);
  }

  @Override
  public byte[] encode() {
    byte statusByte = (byte) status.ordinal();
    return new byte[] { statusByte };
  }

  public enum Status {
    SUCCESSFUL,
    FAILED_NO_REASON,
    FAILED_NOT_A_SUPPORTED_MESSAGE_TYPE,
    FAILED_NOT_A_SUPPORTED_OPERATION,
    FAILED_UNABLE_TO_PASS_TO_SERIAL_PORT,
    FAILED_AUTHENTICATION_FAILURE,
    FAILED_MOBILE_ID_LOOKUP_FAILURE,
    FAILED_NON_ZERO_SEQUENCE_NUMBER_SAME_AS_LAST_RECEIVED_MESSAGE,
    FAILED_MESSAGE_AUTHENTICATION_FAILURE,
    FAILED_MESSAGE_FORMAT_FAILURE,
    FAILED_PARAMETER_UPDATE_FAILURE;
  }

}
