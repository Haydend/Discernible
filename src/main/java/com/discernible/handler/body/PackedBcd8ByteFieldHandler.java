package com.discernible.handler.body;

public class PackedBcd8ByteFieldHandler extends PackedBcdFieldHandler {

  private static final int FIELD_BYTE_LENGTH = 8;

  public PackedBcd8ByteFieldHandler() {
    super(FIELD_BYTE_LENGTH);
  }

}
