package com.discernible.handler.body;

import java.nio.charset.StandardCharsets;

import com.discernible.handler.FieldHandler;
import com.discernible.message.body.AppVersionField;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class AppVersionFieldHandler implements FieldHandler<AppVersionField> {

  public AppVersionField decode(JBBPBitInputStream messageBytes) {

    byte[] fieldBytes = ByteUtils.getFieldBytes(3, messageBytes);
    String fieldString = new String(fieldBytes, StandardCharsets.UTF_8);

    int version = Integer.parseInt(fieldString.substring(0, 2));
    char subVersion = fieldString.charAt(2);

    return new AppVersionField(version, subVersion);
  }

  @Override
  public byte[] encode(AppVersionField field) {
    String versionString = new Integer(field.getVersion()).toString() + new Character(field.getSubVersion()).toString();
    return versionString.getBytes(StandardCharsets.UTF_8);
  }

}
