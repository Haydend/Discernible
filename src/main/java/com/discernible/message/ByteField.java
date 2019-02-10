package com.discernible.message;

import com.discernible.util.ByteUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ByteField implements Field {

  private final byte[] field;

  @Override
  public byte[] encode() {
    return ByteUtils.prependFieldLength(field);
  }

}
