package com.discernible.handler.header.options.extension;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.LmDirectRouting;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.JBBPParser;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class LmDirectRoutingFieldHandler implements FieldHandler<LmDirectRouting> {

  private static final JBBPParser LM_DIRECT_ROUTING = JBBPParser.prepare("byte fieldLength;byte version;byte destination;byte source;");

  @Override
  public LmDirectRouting decode(JBBPBitInputStream messageBytes) {
    ByteUtils.parse(messageBytes, LM_DIRECT_ROUTING);

    return new LmDirectRouting();
  }

  @Override
  public byte[] encode(LmDirectRouting lmDirectRouting) {
    byte[] messageBytes = new byte[3];
    messageBytes[0] = 0x01; // Routing Version

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
