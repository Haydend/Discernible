package com.discernible.message.body;

import java.util.Queue;

public class PackedBcd8ByteField extends PackedBcdField {

  public PackedBcd8ByteField(String value) {
    super(value, 8);
  }

  public static PackedBcd8ByteField decode(Queue<Byte> messageBytes) {

    PackedBcdField packedBcdField = PackedBcdField.decode(messageBytes, 8);

    return new PackedBcd8ByteField(packedBcdField.getValue());
  }

}
