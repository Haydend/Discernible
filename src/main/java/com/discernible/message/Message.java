package com.discernible.message;

import java.util.Queue;

import com.discernible.message.body.MessageBody;
import com.discernible.message.header.options.OptionsHeader;

import lombok.Data;

@Data
public class Message {

  private final OptionsHeader optionHeader;
  private final MessageBody messageBody;

  public static Message decode(Queue<Byte> messageBytes) {

    OptionsHeader optionsHeader = OptionsHeader.decode(messageBytes);
    MessageBody messageBody = MessageBody.decode(messageBytes);

    return new Message(optionsHeader, messageBody);
  }

  public byte[] encode() {

    byte[] optionHeaderBytes = optionHeader.encode();
    byte[] headerBytes = messageBody.encodeMessageHeader();
    byte[] bodyBytes = messageBody.encodeBody();

    byte[] messageBytes = new byte[optionHeaderBytes.length + headerBytes.length + bodyBytes.length];

    System.arraycopy(optionHeaderBytes, 0, messageBytes, 0, optionHeaderBytes.length);
    System.arraycopy(headerBytes, 0, messageBytes, optionHeaderBytes.length, headerBytes.length);
    System.arraycopy(bodyBytes, 0, messageBytes, optionHeaderBytes.length + headerBytes.length, bodyBytes.length);

    return messageBytes;
  }

}
