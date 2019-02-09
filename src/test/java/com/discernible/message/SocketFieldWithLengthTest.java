package com.discernible.message;

import org.junit.Assert;
import org.junit.Test;

public class SocketFieldWithLengthTest {

  @Test
  public void test_encode() {

    // Given
    SocketFieldWithLength socketFieldWithLength = new SocketFieldWithLength("192.168.0.1", 5000);

    // When
    byte[] actualBytes = socketFieldWithLength.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x06, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88 }, actualBytes);

  }

}
