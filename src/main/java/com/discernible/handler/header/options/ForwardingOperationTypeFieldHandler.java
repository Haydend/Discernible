package com.discernible.handler.header.options;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.ForwardingOperationType;

public class ForwardingOperationTypeFieldHandler implements FieldHandler<ForwardingOperationType> {

  @Override
  public ForwardingOperationType decode(ByteInputStream in) {
    byte byteToMatch = (byte) in.read();

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
  public void encode(ForwardingOperationType field, ByteOutputStream out) {

    switch (field) {
      case FORWARD:
        out.write(0x00);
        break;
      case PROXY:
        out.write(0x01);
        break;
      case FORWARD_WITH_LOOKUP:
        out.write(0x02);
        break;
      default:
        throw new IllegalArgumentException(String.format("No Byte code found for Forwarding Operation Type: %s", field));
    }
  }

}
