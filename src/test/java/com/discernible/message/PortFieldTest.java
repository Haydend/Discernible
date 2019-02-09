package com.discernible.message;

import org.junit.Assert;
import org.junit.Test;

public class PortFieldTest {

  @Test
  public void test_encode() {

    // Given
    PortField portField = new PortField(5000);

    // When
    byte[] actualBytes = portField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x13, (byte) 0x88 }, actualBytes);
  }

}
