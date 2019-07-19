package com.discernible.handler;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class UnsignedShortFieldTest {

  private final UnsignedShortFieldHandler fieldHandler = new UnsignedShortFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Short value = 6;

    // When
    byte[] actualBytes = fieldHandler.encode(value);

    // Then
    Assert.assertArrayEquals(new byte[] {0x06}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x06}));

    // When
    short value = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(6, value);
  }

}
