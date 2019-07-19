package com.discernible.message.body;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.PackedBcd8ByteFieldHandler;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class PackedBcd8ByteFieldTest {

  private PackedBcd8ByteFieldHandler packedBcd8ByteFieldHandler = new PackedBcd8ByteFieldHandler();

  @Test
  public void test_encode() {

    // Given
    String packedBcd8ByteField = "4532459871";

    // When
    byte[] fieldBytes = packedBcd8ByteFieldHandler.encode(packedBcd8ByteField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x45, 0x32, 0x45, (byte) 0x98, 0x71, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(new ByteArrayInputStream(
        new byte[] {(byte) 0x45, (byte) 0x32, (byte) 0x45, (byte) 0x98, (byte) 0x71, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}));

    // When
    String field = packedBcd8ByteFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals("4532459871", field);
  }


}
