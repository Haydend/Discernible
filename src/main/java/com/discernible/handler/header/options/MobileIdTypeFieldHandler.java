package com.discernible.handler.header.options;

import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.util.ByteUtils;

public class MobileIdTypeFieldHandler implements FieldHandler<MobileIdTypeField> {

  @Override
  public MobileIdTypeField decode(Queue<Byte> messageBytes) {

    messageBytes.poll(); // Throw away field length.

    MobileIdType mobileIdType = MobileIdTypeField.MobileIdType.values()[messageBytes.poll()];

    return new MobileIdTypeField(mobileIdType);
  }

  @Override
  public byte[] encode(MobileIdTypeField field) {

    byte[] messageBytes = new byte[1];
    messageBytes[0] = (byte) field.getMobileIdType().ordinal();

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
