package com.discernible.message.body.type1;

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

    int firstDigit = messageBytes.poll();
    int secondDigit = messageBytes.poll();
    int version = (firstDigit * 10) + secondDigit;

    char subVersion = new String(ByteUtils.getFieldBytes(1, messageBytes), StandardCharsets.UTF_8).charAt(0);

    return new AppVersionField(version, subVersion);
  }

  @Override
  public byte[] encode() {

    int firstDigit = version / 10;
    int secondDigit = version - (firstDigit * 10);

    byte[] bytes = new byte[] { (byte) firstDigit, (byte) secondDigit, (byte) subVersion };

    return bytes;
  }

}
