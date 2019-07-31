package com.discernible.handler.header.options.extension;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.LmDirectRouting;

public class LmDirectRoutingFieldHandler implements FieldHandler<LmDirectRouting> {

  @Override
  public LmDirectRouting decode(ByteInputStream in) {
    in.read(); // Throw away field length
    in.read(); // Throw away version
    in.read(); // Throw away destination
    in.read(); // Throw away source

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
