package com.discernible.handler.body;

public class PackedBcd10ByteFieldHandler extends PackedBcdFieldHandler {

  private static final int FIELD_BYTE_LENGTH = 10;

  public PackedBcd10ByteFieldHandler() {
    super(FIELD_BYTE_LENGTH);
  }

}
