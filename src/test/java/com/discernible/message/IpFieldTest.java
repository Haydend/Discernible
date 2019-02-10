package com.discernible.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

  @Test
  public void test_encode() {

    // Given
    IpField ipField = new IpField("192.168.0.1");

    // When
    byte[] actualBytes = ipField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { (byte) 0xC0, (byte) 0xA8, 0x00, 0x01 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01));

    // When
    IpField ipField = IpField.decode(bytes);

    // Then
    Assert.assertEquals("192.168.0.1", ipField.getIP());
  }

}
