package com.discernible.message.body.type1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class AppVersionFieldTest {

  @Test
  public void test_encode() {

    // Given
    AppVersionField appVersionField = new AppVersionField(65, 'b');

    // When
    byte[] fieldBytes = appVersionField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x06, 0x05, 0x62 }, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x06, (byte) 0x05, (byte) 0x62));

    // When
    AppVersionField appVersionField = AppVersionField.decode(bytes);

    // Then
    Assert.assertEquals(65, appVersionField.getVersion());
    Assert.assertEquals('b', appVersionField.getSubVersion());
  }

}
