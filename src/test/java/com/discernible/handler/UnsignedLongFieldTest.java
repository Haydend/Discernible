package com.discernible.handler;

import org.junit.Assert;
import org.junit.Test;

public class UnsignedLongFieldTest {

  private final UnsignedLongFieldHandler fieldHandler = new UnsignedLongFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Long value = 50000000L;
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(value, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x02, (byte) 0xFA, (byte) 0xF0, (byte) 0x80}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {0x02, (byte) 0xFA, (byte) 0xF0, (byte) 0x80};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    long value = fieldHandler.decode(in);

    // Then
    Assert.assertEquals(50000000L, value);
  }

}
