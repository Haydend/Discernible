package com.discernible.handler;

import java.nio.charset.StandardCharsets;

public class Ascii8BitFieldHandler implements FieldHandler<String> {

  public String decode(ByteInputStream in) {

    int fieldLength = in.read();

    byte[] fieldBytes = in.read(fieldLength);

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    return field;
  }

  @Override
  public void encode(String field, ByteOutputStream out) {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    out.write(fieldBytes.length);
    out.write(fieldBytes);
  }

}
