package com.discernible.handler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class UnsignedIntegerFieldTest {

  private final UnsignedIntegerFieldHandler fieldHandler = new UnsignedIntegerFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Integer value = 5000;
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(value, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x13, (byte) 0x88}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x13, (byte) 0x88));

    // When
    int value = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(5000, value);
  }

}
