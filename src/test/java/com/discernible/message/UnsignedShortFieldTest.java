package com.discernible.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class UnsignedShortFieldTest {

  @Test
  public void test_encode() {

    // Given
    UnsignedShortField portField = new UnsignedShortField((short) 6);

    // When
    byte[] actualBytes = portField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x06 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x06));

    // When
    UnsignedShortField unsignedShortField = UnsignedShortField.decode(bytes);

    // Then
    Assert.assertEquals(6, unsignedShortField.getValue());

  }

}
