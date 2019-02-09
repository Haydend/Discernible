package com.discernible.message;

import java.nio.charset.StandardCharsets;

import lombok.Data;

@Data
public class Ascii8BitField implements Field {

  private final String field;

  @Override
  public byte[] encode() {
	  return field.getBytes(StandardCharsets.UTF_8);
  }

}
