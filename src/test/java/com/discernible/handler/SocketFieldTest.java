package com.discernible.handler;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.Socket;

public class SocketFieldTest {

  private final SocketFieldHandler fieldHandler = new SocketFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Socket socket = new Socket("192.168.0.1", 5000);
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(socket, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x06, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88}, out.toByteArray());
  }

  @Test
  public void test_decode() {
    // Given
    byte[] bytes = new byte[] {(byte) 0x06, (byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01, (byte) 0x13, (byte) 0x88};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    Socket socketField = fieldHandler.decode(in);

    // Then
    Assert.assertEquals("192.168.0.1", socketField.getIpText());
    Assert.assertEquals(5000, socketField.getPort());
  }

}
