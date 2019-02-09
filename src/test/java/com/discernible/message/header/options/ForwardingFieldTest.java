package com.discernible.message.header.options;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.header.options.ForwardingField.ForwardingOperationType;
import com.discernible.message.header.options.ForwardingField.Protocol;

public class ForwardingFieldTest {

  @Test
  public void test_encode() {

    // Given
    ForwardingField forwardingField = new ForwardingField("192.168.0.1", 5000, Protocol.TCP, ForwardingOperationType.FORWARD);

    // When
    byte[] actualFieldBytes = forwardingField.encode();

    // Then
    // ------------------------- Field Length | IP Address / Forwarding Address ....| Forwarding Port .| TCP | Type
    Assert.assertArrayEquals(new byte[] { 0x08, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88, 0x06, 0x00 }, actualFieldBytes);

  }

}
