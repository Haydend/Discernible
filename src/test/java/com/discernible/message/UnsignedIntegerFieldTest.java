package com.discernible.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class UnsignedIntegerFieldTest {

  @Test
  public void test_encode() {

    // Given
    UnsignedIntegerField portField = new UnsignedIntegerField(5000);

    // When
    byte[] actualBytes = portField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x13, (byte) 0x88 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x13, (byte) 0x88));

    // When
    UnsignedIntegerField portField = UnsignedIntegerField.decode(bytes);

    // Then
    Assert.assertEquals(5000, portField.getValue());

  }

}
