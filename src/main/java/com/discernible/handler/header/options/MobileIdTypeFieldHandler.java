package com.discernible.handler.header.options;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class MobileIdTypeFieldHandler implements FieldHandler<MobileIdTypeField> {

  @Override
  public MobileIdTypeField decode(JBBPBitInputStream messageBytes) {

    ByteUtils.getByte(messageBytes); // Throw away field length.

    MobileIdType mobileIdType = MobileIdTypeField.MobileIdType.values()[ByteUtils.getByte(messageBytes)];

    return new MobileIdTypeField(mobileIdType);
  }

  @Override
  public byte[] encode(MobileIdTypeField field) {

    byte[] messageBytes = new byte[1];
    messageBytes[0] = (byte) field.getMobileIdType().ordinal();

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
