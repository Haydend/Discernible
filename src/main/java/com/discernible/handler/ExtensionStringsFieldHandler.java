package com.discernible.handler;

import java.nio.charset.StandardCharsets;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtensionStringsFieldHandler implements FieldHandler<String> {

  public String decode(Queue<Byte> messageBytes) {

    byte[] fieldBytes = ByteUtils.getFieldBytes(messageBytes.size(), messageBytes);

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    return field;
  }

  @Override
  public void encode(String field, ByteOutputStream out) {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    out.write(fieldBytes);
  }

}
