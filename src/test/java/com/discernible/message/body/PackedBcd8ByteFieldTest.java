package com.discernible.message.body;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

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
    Queue<Byte> bytes =
        new LinkedList<Byte>(Arrays.asList((byte) 0x45, (byte) 0x32, (byte) 0x45, (byte) 0x98, (byte) 0x71, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF));

    // When
    String field = packedBcd8ByteFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals("4532459871", field);
  }


}
