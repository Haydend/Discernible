package com.discernible.message.body;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.AppVersionFieldHandler;

public class AppVersionFieldTest {

  private AppVersionFieldHandler appVersionFieldHandler = new AppVersionFieldHandler();

  @Test
  public void test_encode() {

    // Given
    AppVersionField appVersionField = new AppVersionField(39, 'l');

    // When
    byte[] fieldBytes = appVersionFieldHandler.encode(appVersionField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x33, 0x39, 0x6C}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x33, (byte) 0x39, (byte) 0x6C));

    // When
    AppVersionField appVersionField = appVersionFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(39, appVersionField.getVersion());
    Assert.assertEquals('l', appVersionField.getSubVersion());
  }

}
