package com.discernible.handler;

import org.junit.Assert;
import org.junit.Test;

public class Ascii8BitFieldHandlerTest {

  private final Ascii8BitFieldHandler fieldHandler = new Ascii8BitFieldHandler();

  @Test
  public void test_encode() {

    // Given
    String value = "test";
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(value, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x04, 0x74, 0x65, 0x73, 0x74}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x04, (byte) 0x74, (byte) 0x65, (byte) 0x73, (byte) 0x74};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    String ascii8BitField = fieldHandler.decode(in);

    // Then
    Assert.assertEquals("test", ascii8BitField);
  }

}
