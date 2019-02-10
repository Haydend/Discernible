package com.discernible.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class Ascii8BitFieldTest {

  @Test
  public void test_encode() {

    // Given
    Ascii8BitField ascii8BitField = new Ascii8BitField("test");

    // When
    byte[] actualBytes = ascii8BitField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x04, 0x74, 0x65, 0x73, 0x74 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x04, (byte) 0x74, (byte) 0x65, (byte) 0x73, (byte) 0x74));

    // When
    Ascii8BitField ascii8BitField = Ascii8BitField.decode(bytes);

    // Then
    Assert.assertEquals("test", ascii8BitField.getField());
  }

}
