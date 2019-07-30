package com.discernible.handler.header.options;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;

public class MobileIdTypeFieldHandler implements FieldHandler<MobileIdTypeField> {

  @Override
  public MobileIdTypeField decode(Queue<Byte> messageBytes) {

    messageBytes.poll(); // Throw away field length.

    MobileIdType mobileIdType = MobileIdTypeField.MobileIdType.values()[messageBytes.poll()];

    return new MobileIdTypeField(mobileIdType);
  }

  @Override
  public void encode(MobileIdTypeField field, ByteOutputStream out) {
    out.write(0x01);
    out.write(field.getMobileIdType().ordinal());
  }

}
