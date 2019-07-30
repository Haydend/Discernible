package com.discernible.handler.body;

import java.nio.charset.StandardCharsets;
import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.body.AppVersionField;
import com.discernible.util.ByteUtils;

public class AppVersionFieldHandler implements FieldHandler<AppVersionField> {

  public AppVersionField decode(Queue<Byte> messageBytes) {

    byte[] fieldBytes = ByteUtils.getFieldBytes(3, messageBytes);
    String fieldString = new String(fieldBytes, StandardCharsets.UTF_8);

    int version = Integer.parseInt(fieldString.substring(0, 2));
    char subVersion = fieldString.charAt(2);

    return new AppVersionField(version, subVersion);
  }

  @Override
  public void encode(AppVersionField field, ByteOutputStream out) {
    String versionString = new Integer(field.getVersion()).toString() + new Character(field.getSubVersion()).toString();
    out.write(versionString.getBytes(StandardCharsets.UTF_8));
  }

}
