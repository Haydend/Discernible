package com.discernible.handler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class ByteFieldTest {

  private final ByteFieldHandler fieldHandler = new ByteFieldHandler();

  @Test
  public void test_encode() {

    // Given
    byte[] byteField = new byte[] {0x02, 0x03, 0x04};
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(byteField, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x03, 0x02, 0x03, 0x04}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x01));

    // When
    byte[] byteField = fieldHandler.decode(bytes);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, 0x02}, byteField);
  }

}
