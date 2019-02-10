package com.discernible.message.header.options.extension;

import com.discernible.message.Field;
import com.discernible.util.ByteUtils;

public class LmDirectRouting implements Field {

  @Override
  public byte[] encode() {

    byte[] messageBytes = new byte[3];
    messageBytes[0] = 0x01; // Routing Version

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
