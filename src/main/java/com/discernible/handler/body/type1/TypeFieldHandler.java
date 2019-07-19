package com.discernible.handler.body.type1;

import com.discernible.handler.FieldHandler;
import com.discernible.message.body.Message.MessageType;
import com.discernible.message.body.type1.TypeField;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class TypeFieldHandler implements FieldHandler<TypeField> {

  @Override
  public TypeField decode(JBBPBitInputStream messageBytes) {

    byte typeByte = ByteUtils.getByte(messageBytes);
    MessageType messageType = MessageType.values()[(int) typeByte];

    return new TypeField(messageType);
  }

  @Override
  public byte[] encode(TypeField field) {
    byte typeByte = (byte) field.getType().ordinal();
    return new byte[] {typeByte};
  }

}
