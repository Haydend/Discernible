package com.discernible.message;

import java.nio.charset.StandardCharsets;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtensionStringsField implements Field {

  private String field;


  public static ExtensionStringsField decode(Queue<Byte> messageBytes) {

    byte[] fieldBytes = ByteUtils.getFieldBytes(messageBytes.size(), messageBytes);

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    return new ExtensionStringsField(field);
  }

  @Override
  public byte[] encode() {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    return fieldBytes;
  }

}
