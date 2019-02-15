package com.discernible.message;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataListField implements Field {

  private Map<String, String> data = new HashMap<>();

  public static DataListField decode(Queue<Byte> messageBytes) {

    byte[] fieldBytes = ByteUtils.getFieldBytes(messageBytes.size(), messageBytes);

    String field = new String(fieldBytes, StandardCharsets.UTF_8);

    String[] fieldLines = field.split("\0");

    Map<String, String> data = new HashMap<>();
    for (String fieldLine : fieldLines) {
      String[] lineValues = fieldLine.split(":");

      String key = lineValues[0];
      String value = lineValues[1];

      data.put(key, value);
    }

    return new DataListField(data);
  }

  @Override
  public byte[] encode() {

    StringBuilder fieldBuilder = new StringBuilder();
    for (Entry<String, String> entry : data.entrySet()) {
      fieldBuilder.append(entry.getKey())
          .append(":")
          .append(entry.getValue())
          .append("\0");
    }

    byte[] fieldBytes = fieldBuilder.toString().getBytes(StandardCharsets.UTF_8);
    return fieldBytes;
  }

  public void add(String key, String value) {
    data.put(key, value);
  }

}
