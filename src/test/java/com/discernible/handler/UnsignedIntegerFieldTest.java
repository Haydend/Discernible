package com.discernible.handler;

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
    byte[] bytes = new byte[] {(byte) 0x13, (byte) 0x88};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    int value = fieldHandler.decode(in);

    // Then
    Assert.assertEquals(5000, value);
  }

}
