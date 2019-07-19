package com.discernible.handler;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.Socket;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class SocketFieldTest {

  private final SocketFieldHandler fieldHandler = new SocketFieldHandler();

  @Test
  public void test_encode() {

    // Given
    Socket socket = new Socket("192.168.0.1", 5000);

    // When
    byte[] actualBytes = fieldHandler.encode(socket);

    // Then
    Assert.assertArrayEquals(new byte[] {0x06, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88}, actualBytes);
  }

  @Test
  public void test_decode() {
    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x06, (byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01, (byte) 0x13, (byte) 0x88}));

    // When
    Socket socketField = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals("192.168.0.1", socketField.getIpText());
    Assert.assertEquals(5000, socketField.getPort());
  }

}
