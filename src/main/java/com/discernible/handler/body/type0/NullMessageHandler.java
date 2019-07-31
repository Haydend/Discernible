package com.discernible.handler.body.type0;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.message.body.type0.NullMessage;

public class NullMessageHandler {

  public NullMessage decodeBody(ByteInputStream in) {
    return new NullMessage();
  }

  public void encodeBody(NullMessage message, ByteOutputStream output) {}

}
