package com.discernible.handler.body.type0;

import java.util.Queue;

import com.discernible.message.body.type0.NullMessage;

public class NullMessageHandler {

  public NullMessage decodeBody(Queue<Byte> messageBytes) {
    return new NullMessage();
  }

  public byte[] encodeBody(NullMessage message) {
    return new byte[0];
  }

}
