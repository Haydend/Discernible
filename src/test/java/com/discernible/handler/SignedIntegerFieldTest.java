package com.discernible.handler;

import org.junit.Assert;
import org.junit.Test;

public class SignedIntegerFieldTest {

  private final SignedIntegerFieldHandler fieldHandler = new SignedIntegerFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Integer value = 4;
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(value, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x00, 0x00, 0x00, 0x04}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    int integerField = fieldHandler.decode(in);

    // Then
    Assert.assertEquals(4, integerField);
  }

}
