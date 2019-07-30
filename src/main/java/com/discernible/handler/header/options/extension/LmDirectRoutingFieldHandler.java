package com.discernible.handler.header.options.extension;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.LmDirectRouting;

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
  public void encode(LmDirectRouting lmDirectRouting, ByteOutputStream out) {
    out.write(0x03); // Field length
    out.write(0x01); // Version
    out.write(0x00); // Destination
    out.write(0x00); // Source
  }

}
