package com.discernible.handler.header.options;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.Protocol;

public class ProtocolFieldHandler implements FieldHandler<Protocol> {

  @Override
  public Protocol decode(ByteInputStream in) {
    byte byteToMatch = (byte) in.read();

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
  public void encode(Protocol field, ByteOutputStream out) {

    switch (field) {
      case TCP:
        out.write(0x06);
        break;
      case UDP:
        out.write(0x11);
        break;
      default:
        throw new IllegalArgumentException(String.format("No Byte code found for Protocol: %s", field));
    }
  }

}
