package com.discernible.handler.header.options.extension;

import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.LmDirectRouting;
import com.discernible.util.ByteUtils;

public class LmDirectRoutingFieldHandler implements FieldHandler<LmDirectRouting> {

  @Override
  public LmDirectRouting decode(Queue<Byte> messageBytes) {
    messageBytes.poll(); // Throw away field length
    messageBytes.poll(); // Throw away version
    messageBytes.poll(); // Throw away destination
    messageBytes.poll(); // Throw away source

    return new LmDirectRouting();
  }

  @Override
  public byte[] encode(LmDirectRouting lmDirectRouting) {
    byte[] messageBytes = new byte[3];
    messageBytes[0] = 0x01; // Routing Version

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
