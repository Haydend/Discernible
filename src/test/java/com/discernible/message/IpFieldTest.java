package com.discernible.message;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.util.TestUtils;

public class IpFieldTest {

  @Test
  public void test_badFormat() {

    // When
    IllegalArgumentException e = TestUtils.catchException(() -> new IpField("Bad IP Format"));

    // Then
    Assert.assertEquals("Provided IP is not in correct format (xxx.xxx.xxx.xxx): Bad IP Format", e.getMessage());
  }

  public void test_encode() {

    // Given
    IpField ipField = new IpField("192.168.0.1");

    // When
    byte[] actualBytes = ipField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { (byte) 0xC0, (byte) 0xA8, 0x00, 0x01 }, actualBytes);
  }

}
