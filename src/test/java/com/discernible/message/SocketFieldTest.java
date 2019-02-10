package com.discernible.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
    Assert.assertArrayEquals(new byte[] { 0x06, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88 }, actualBytes);
  }

  @Test
  public void test_decode() {
    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x06, (byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01, (byte) 0x13, (byte) 0x88));

    // When
    SocketField socketField = SocketField.decode(bytes);

    // Then
    Assert.assertEquals("192.168.0.1", socketField.getIP());
    Assert.assertEquals(5000, socketField.getPort());
  }

}
