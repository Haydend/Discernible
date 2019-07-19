package com.discernible.handler;

import java.nio.charset.StandardCharsets;

import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtensionStringsFieldHandler implements FieldHandler<String> {

  public String decode(JBBPBitInputStream messageBytes) {

    byte[] fieldBytes = ByteUtils.getFieldBytes(-1, messageBytes);

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    return field;
  }

  @Override
  public byte[] encode(String field) {
    byte[] fieldBytes = field.getBytes(StandardCharsets.UTF_8);
    return fieldBytes;
  }

}
