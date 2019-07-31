package com.discernible.handler;

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
    byte[] bytes = new byte[] {(byte) 0x06};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    short value = fieldHandler.decode(in);

    // Then
    Assert.assertEquals(6, value);
  }

}
