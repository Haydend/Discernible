package com.discernible.handler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.IP;

public class IpFieldTest {

  private final IpFieldHandler fieldHandler = new IpFieldHandler();

  // @Test
  // public void test_badFormat() {
  //
  // // When
  // IllegalArgumentException e = TestUtils.catchException(() -> new IpFieldHandler("Bad IP Format"));
  //
  // // Then
  // Assert.assertEquals("Provided IP is not in correct format (xxx.xxx.xxx.xxx): Bad IP Format", e.getMessage());
  // }

  @Test
  public void test_encode() {

    // Given
    IP ipField = new IP("192.168.0.1");
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(ipField, out);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0xC0, (byte) 0xA8, 0x00, 0x01}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01));

    // When
    IP ipField = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals("192.168.0.1", ipField.getIP());
  }

}
