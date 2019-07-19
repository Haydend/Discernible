package com.discernible.handler.body.type0;

import com.discernible.message.body.type0.NullMessage;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class NullMessageHandler {

  public NullMessage decodeBody(JBBPBitInputStream messageBytes) {
    return new NullMessage();
  }

  public byte[] encodeBody(NullMessage message) {
    return new byte[0];
  }

}
