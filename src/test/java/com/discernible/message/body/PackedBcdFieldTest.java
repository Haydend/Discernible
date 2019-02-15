package com.discernible.message.body;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class PackedBcdFieldTest {

  @Test
  public void test_encode() {

    // Given
    PackedBcdField packedBcdField = new PackedBcdField("4532459871", 8);

    // When
    byte[] fieldBytes = packedBcdField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] {0x45, 0x32, 0x45, (byte) 0x98, 0x71, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes =
        new LinkedList<Byte>(Arrays.asList((byte) 0x45, (byte) 0x32, (byte) 0x45, (byte) 0x98, (byte) 0x71, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF));

    // When
    PackedBcdField field = PackedBcdField.decode(bytes, 8);

    // Then
    Assert.assertEquals("4532459871", field.getValue());
  }


}
