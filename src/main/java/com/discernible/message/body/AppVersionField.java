package com.discernible.message.body;

import java.nio.charset.StandardCharsets;
import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppVersionField implements Field {

  private int version;
  private char subVersion;

  public static AppVersionField decode(Queue<Byte> messageBytes) {

    byte[] fieldBytes = ByteUtils.getFieldBytes(3, messageBytes);
    String fieldString = new String(fieldBytes, StandardCharsets.UTF_8);

    int version = Integer.parseInt(fieldString.substring(0, 2));
    char subVersion = fieldString.charAt(2);

    return new AppVersionField(version, subVersion);
  }

  @Override
  public byte[] encode() {
    String versionString = new Integer(version).toString() + new Character(subVersion).toString();
    return versionString.getBytes(StandardCharsets.UTF_8);
  }

}
