package com.discernible.message.body;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.PackedBcd8ByteFieldHandler;

public class PackedBcd8ByteFieldTest {

  private PackedBcd8ByteFieldHandler packedBcd8ByteFieldHandler = new PackedBcd8ByteFieldHandler();

  @Test
  public void test_encode() {

    // Given
    String packedBcd8ByteField = "4532459871";
    ByteOutputStream out = new ByteOutputStream();

    // When
    packedBcd8ByteFieldHandler.encode(packedBcd8ByteField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {0x45, 0x32, 0x45, (byte) 0x98, 0x71, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x45, (byte) 0x32, (byte) 0x45, (byte) 0x98, (byte) 0x71, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    String field = packedBcd8ByteFieldHandler.decode(in);

    // Then
    Assert.assertEquals("4532459871", field);
  }


}
