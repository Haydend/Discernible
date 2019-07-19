package com.discernible.handler;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class SignedIntegerFieldTest {

  private final SignedIntegerFieldHandler fieldHandler = new SignedIntegerFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Integer value = 4;

    // When
    byte[] actualBytes = fieldHandler.encode(value);

    // Then
    Assert.assertArrayEquals(new byte[] {0x00, 0x00, 0x00, 0x04}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04}));

    // When
    int integerField = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(4, integerField);
  }

}
