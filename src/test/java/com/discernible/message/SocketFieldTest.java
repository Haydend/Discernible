package com.discernible.message;

import org.junit.Assert;
import org.junit.Test;

public class SocketFieldTest {

  @Test
  public void test_encode() {

    // Given
    SocketField socketField = new SocketField("192.168.0.1", 5000);

    // When
    byte[] actualBytes = socketField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88 }, actualBytes);
  }

  @Test
  public void test_encode_with_length() {

    // Given
    WithLength<SocketField> socketFieldWithLength = new SocketField("192.168.0.1", 5000).withLength();

    // When
    byte[] actualBytes = socketFieldWithLength.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x06, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88 }, actualBytes);
  }

}
