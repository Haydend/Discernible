package com.discernible.message.body;

import java.util.Queue;

public class PackedBcd10ByteField extends PackedBcdField {

  private static final int FIELD_BYTE_LENGTH = 10;

  public PackedBcd10ByteField(String value) {
    super(value, FIELD_BYTE_LENGTH);
  }

  public static PackedBcd10ByteField decode(Queue<Byte> messageBytes) {

    PackedBcdField packedBcdField = PackedBcdField.decode(messageBytes, FIELD_BYTE_LENGTH);

    return new PackedBcd10ByteField(packedBcdField.getValue());
  }

}
