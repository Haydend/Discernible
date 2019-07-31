package com.discernible.handler;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DataListFieldHandler implements FieldHandler<Map<String, String>> {

  public Map<String, String> decode(ByteInputStream in) {

    byte[] fieldBytes = in.readAll();

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    String[] fieldLines = field.split("\0");

    Map<String, String> data = new HashMap<>();
    for (String fieldLine : fieldLines) {
      String[] lineValues = fieldLine.split(":");

      String key = lineValues[0];
      String value = lineValues.length >= 2 ? lineValues[1] : "";

      data.put(key, value);
    }

    return data;
  }

  @Override
  public void encode(Map<String, String> data, ByteOutputStream out) {

    StringBuilder fieldBuilder = new StringBuilder();
    for (Entry<String, String> entry : data.entrySet()) {
      fieldBuilder.append(entry.getKey())
          .append(":")
          .append(entry.getValue())
          .append("\0");
    }

    byte[] fieldBytes = fieldBuilder.toString().getBytes(StandardCharsets.UTF_8);
    out.write(fieldBytes);
  }

}
