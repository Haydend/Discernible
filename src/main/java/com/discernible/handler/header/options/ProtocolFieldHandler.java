package com.discernible.handler.header.options;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.Protocol;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class ProtocolFieldHandler implements FieldHandler<Protocol> {

  @Override
  public Protocol decode(JBBPBitInputStream messageBytes) {
    byte byteToMatch = ByteUtils.getByte(messageBytes);

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
