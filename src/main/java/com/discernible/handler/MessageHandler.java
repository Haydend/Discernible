package com.discernible.handler;

import java.util.Queue;

import com.discernible.handler.body.MessageBodyHandler;
import com.discernible.handler.header.options.OptionsHeaderFieldHandler;
import com.discernible.message.Message;
import com.discernible.message.body.MessageBody;
import com.discernible.message.header.options.OptionsHeader;

import lombok.Data;

@Data
public class MessageHandler {

  private final OptionsHeaderFieldHandler optionsHeaderFieldHandler = new OptionsHeaderFieldHandler();
  private final MessageBodyHandler messageBodyHandler = new MessageBodyHandler();

  public Message decode(Queue<Byte> messageBytes, boolean sentFromLmu) {

    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(messageBytes);
    MessageBody messageBody = messageBodyHandler.decode(messageBytes, sentFromLmu);

    return new Message(optionsHeader, messageBody);
  }

  public byte[] encode(Message message, boolean sentFromLmu) {

    byte[] optionHeaderBytes = optionsHeaderFieldHandler.encode(message.getOptionHeader());
    byte[] headerBytes = messageBodyHandler.encode(message.getMessageBody());
    byte[] bodyBytes = messageBodyHandler.encodeBody(message.getMessageBody(), sentFromLmu);

    byte[] messageBytes = new byte[optionHeaderBytes.length + headerBytes.length + bodyBytes.length];

    System.arraycopy(optionHeaderBytes, 0, messageBytes, 0, optionHeaderBytes.length);
    System.arraycopy(headerBytes, 0, messageBytes, optionHeaderBytes.length, headerBytes.length);
    System.arraycopy(bodyBytes, 0, messageBytes, optionHeaderBytes.length + headerBytes.length, bodyBytes.length);

    return messageBytes;
  }

}
