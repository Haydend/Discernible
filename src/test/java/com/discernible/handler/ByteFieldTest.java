package com.discernible.handler;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class ByteFieldTest {

  private final ByteFieldHandler fieldHandler = new ByteFieldHandler();

  @Test
  public void test_encode() {

    // Given
    byte[] byteField = new byte[] {0x02, 0x03, 0x04};

    // When
    byte[] actualBytes = fieldHandler.encode(byteField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x03, 0x02, 0x03, 0x04}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x01}));

    // When
    byte[] byteField = fieldHandler.decode(bytes);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, 0x02}, byteField);
  }

}
