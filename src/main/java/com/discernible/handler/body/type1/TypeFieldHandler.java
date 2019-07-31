package com.discernible.handler.body.type1;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.body.Message.MessageType;
import com.discernible.message.body.type1.TypeField;

public class TypeFieldHandler implements FieldHandler<TypeField> {

  @Override
  public TypeField decode(ByteInputStream in) {

    byte typeByte = (byte) in.read();
    MessageType messageType = MessageType.values()[(int) typeByte];

    return new TypeField(messageType);
  }

  @Override
  public void encode(TypeField field, ByteOutputStream output) {
    output.write(field.getType().ordinal());
  }

}
