package com.discernible.handler.header.options;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.JBBPParser;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;
import com.igormaznitsa.jbbp.model.JBBPFieldUByte;

public class MobileIdTypeFieldHandler implements FieldHandler<MobileIdType> {

  private static final JBBPParser MOBILE_ID_FIELD = JBBPParser.prepare("skip;ubyte value;");

  @Override
  public MobileIdType decode(JBBPBitInputStream messageBytes) {
    int mobileIdTypeIndex = ByteUtils.parse(messageBytes, MOBILE_ID_FIELD).findFieldForNameAndType("value", JBBPFieldUByte.class).getAsInt();
    return MobileIdTypeField.MobileIdType.values()[mobileIdTypeIndex];
  }

  @Override
  public byte[] encode(MobileIdType field) {
    byte[] messageBytes = new byte[1];
    messageBytes[0] = (byte) field.ordinal();

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
