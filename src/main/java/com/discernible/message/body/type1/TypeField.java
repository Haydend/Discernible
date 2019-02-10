package com.discernible.message.body.type1;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.message.body.MessageBody.MessageType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeField implements Field {

  private MessageType type;

  public static TypeField decode(Queue<Byte> messageBytes) {

    byte typeByte = messageBytes.poll();
    MessageType messageType = MessageType.values()[(int) typeByte];

    return new TypeField(messageType);
  }

  @Override
  public byte[] encode() {
    byte typeByte = (byte) type.ordinal();
    return new byte[] { typeByte };
  }

}
