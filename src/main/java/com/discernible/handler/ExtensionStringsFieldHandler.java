package com.discernible.handler;

import java.nio.charset.StandardCharsets;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtensionStringsFieldHandler implements FieldHandler<String> {

  public String decode(ByteInputStream in) {

    byte[] fieldBytes = in.readAll();

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    return field;
  }

  @Override
  public void encode(String field, ByteOutputStream out) {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    out.write(fieldBytes);
  }

}
