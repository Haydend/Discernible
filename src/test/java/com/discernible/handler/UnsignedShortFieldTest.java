package com.discernible.handler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class UnsignedShortFieldTest {

  private final UnsignedShortFieldHandler fieldHandler = new UnsignedShortFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Short value = 6;
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(value, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x06}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x06));

    // When
    short value = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(6, value);
  }

}
