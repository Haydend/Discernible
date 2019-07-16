package com.discernible.handler;

import java.nio.charset.StandardCharsets;
import java.util.Queue;

import com.discernible.util.ByteUtils;

public class Ascii8BitFieldHandler implements FieldHandler<String> {

  public String decode(Queue<Byte> messageBytes) {

    int fieldLength = ByteUtils.getFieldLength(messageBytes);

    byte[] fieldBytes = ByteUtils.getFieldBytes(fieldLength, messageBytes);

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    return field;
  }

  @Override
  public byte[] encode(String field) {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    return ByteUtils.prependFieldLength(fieldBytes);
  }

}
