package com.discernible.handler.body.type0;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.message.body.type0.NullMessage;

public class NullMessageHandler {

  public NullMessage decodeBody(Queue<Byte> messageBytes) {
    return new NullMessage();
  }

  public void encodeBody(NullMessage message, ByteOutputStream output) {}

}
