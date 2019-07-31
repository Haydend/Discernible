package com.discernible.handler.header.options;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;

public class MobileIdTypeFieldHandler implements FieldHandler<MobileIdTypeField> {

  @Override
  public MobileIdTypeField decode(ByteInputStream in) {

    in.read(); // Throw away field length.

    MobileIdType mobileIdType = MobileIdTypeField.MobileIdType.values()[in.read()];

    return new MobileIdTypeField(mobileIdType);
  }

  @Override
  public void encode(MobileIdTypeField field, ByteOutputStream out) {
    out.write(0x01);
    out.write(field.getMobileIdType().ordinal());
  }

}
