package com.discernible.message;

import java.nio.charset.StandardCharsets;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ascii8BitField implements Field {

  private String field;

  public static Ascii8BitField decode(Queue<Byte> messageBytes) {

    int fieldLength = ByteUtils.getFieldLength(messageBytes);

    byte[] fieldBytes = ByteUtils.getFieldBytes(fieldLength, messageBytes);

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    return new Ascii8BitField(field);
  }

  @Override
  public byte[] encode() {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    return ByteUtils.prependFieldLength(fieldBytes);
  }

}
