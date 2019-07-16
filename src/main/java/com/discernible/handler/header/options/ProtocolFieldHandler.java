package com.discernible.handler.header.options;

import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.Protocol;

public class ProtocolFieldHandler implements FieldHandler<Protocol> {

  @Override
  public Protocol decode(Queue<Byte> messageBytes) {
    byte byteToMatch = messageBytes.poll();

    switch (byteToMatch) {
      case 0x06:
        return Protocol.TCP;
      case 0x11:
        return Protocol.UDP;
      default:
        throw new IllegalArgumentException(String.format("No Protocol found for byte: %s", byteToMatch));
    }
  }

  @Override
  public byte[] encode(Protocol field) {

    switch (field) {
      case TCP:
        return new byte[] {0x06};
      case UDP:
        return new byte[] {0x11};
      default:
        throw new IllegalArgumentException(String.format("No Byte code found for Protocol: %s", field));
    }
  }

}
