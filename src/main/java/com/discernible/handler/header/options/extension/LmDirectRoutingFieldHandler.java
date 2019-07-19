package com.discernible.handler.header.options.extension;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.LmDirectRouting;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class LmDirectRoutingFieldHandler implements FieldHandler<LmDirectRouting> {

  @Override
  public LmDirectRouting decode(JBBPBitInputStream messageBytes) {
    ByteUtils.getByte(messageBytes); // Throw away field length
    ByteUtils.getByte(messageBytes); // Throw away version
    ByteUtils.getByte(messageBytes); // Throw away destination
    ByteUtils.getByte(messageBytes); // Throw away source

    return new LmDirectRouting();
  }

  @Override
  public byte[] encode(LmDirectRouting lmDirectRouting) {
    byte[] messageBytes = new byte[3];
    messageBytes[0] = 0x01; // Routing Version

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
