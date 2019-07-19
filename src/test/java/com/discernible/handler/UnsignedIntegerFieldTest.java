package com.discernible.handler;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class UnsignedIntegerFieldTest {

  private final UnsignedIntegerFieldHandler fieldHandler = new UnsignedIntegerFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Integer value = 5000;

    // When
    byte[] actualBytes = fieldHandler.encode(value);

    // Then
    Assert.assertArrayEquals(new byte[] {0x13, (byte) 0x88}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x13, (byte) 0x88}));

    // When
    int value = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(5000, value);
  }

}
