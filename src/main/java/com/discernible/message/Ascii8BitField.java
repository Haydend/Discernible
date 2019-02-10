package com.discernible.message;

import java.nio.charset.StandardCharsets;

import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class Ascii8BitField implements Field {

  private final String field;

  @Override
  public byte[] encode() {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    return ByteUtils.prependFieldLength(fieldBytes);
  }

}
