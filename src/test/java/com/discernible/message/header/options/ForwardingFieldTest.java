package com.discernible.message.header.options;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.header.options.ForwardingFieldHandler;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class ForwardingFieldTest {

  private ForwardingFieldHandler forwardingFieldHandler = new ForwardingFieldHandler();

  @Test
  public void test_encode() {

    // Given
    ForwardingField forwardingField = new ForwardingField("192.168.0.1", 5000, Protocol.TCP, ForwardingOperationType.FORWARD);

    // When
    byte[] actualFieldBytes = forwardingFieldHandler.encode(forwardingField);

    // Then
    // --------------------------------- IP Address / Forwarding Address ....| Forwarding Port .| TCP | Type
    Assert.assertArrayEquals(new byte[] {0x08, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88, 0x06, 0x00}, actualFieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(new ByteArrayInputStream(
        new byte[] {(byte) 0x08, (byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01, (byte) 0x13, (byte) 0x88, (byte) 0x06, (byte) 0x00}));

    // When
    ForwardingField forwardingField = forwardingFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals("192.168.0.1", forwardingField.getIp().getIP());
    Assert.assertEquals(5000, (int) forwardingField.getPort());
    Assert.assertEquals(Protocol.TCP, forwardingField.getForwardingProtocol());
    Assert.assertEquals(ForwardingOperationType.FORWARD, forwardingField.getForwardingOperationType());
  }

}
