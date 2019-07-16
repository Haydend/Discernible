package com.discernible.handler.header.options;

import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.ForwardingOperationType;

public class ForwardingOperationTypeFieldHandler implements FieldHandler<ForwardingOperationType> {

  @Override
  public ForwardingOperationType decode(Queue<Byte> messageBytes) {
    byte byteToMatch = messageBytes.poll();

    switch (byteToMatch) {
      case 0x00:
        return ForwardingOperationType.FORWARD;
      case 0x01:
        return ForwardingOperationType.PROXY;
      case 0x02:
        return ForwardingOperationType.FORWARD_WITH_LOOKUP;
      default:
        throw new IllegalArgumentException(String.format("No Forwarding Operation Type found for byte: %s", byteToMatch));
    }
  }

  @Override
  public byte[] encode(ForwardingOperationType field) {

    switch (field) {
      case FORWARD:
        return new byte[] {0x00};
      case PROXY:
        return new byte[] {0x01};
      case FORWARD_WITH_LOOKUP:
        return new byte[] {0x02};
      default:
        throw new IllegalArgumentException(String.format("No Byte code found for Forwarding Operation Type: %s", field));
    }
  }

}
