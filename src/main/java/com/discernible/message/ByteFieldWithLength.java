package com.discernible.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ByteFieldWithLength extends FieldWithLength {

  private final byte[] field;

  @Override
  protected byte[] encodeField() {
    return field;
  }

}
