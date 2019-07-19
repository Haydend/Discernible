package com.discernible.message.body;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.AppVersionFieldHandler;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

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
    JBBPBitInputStream bytes = new JBBPBitInputStream(new ByteArrayInputStream(
        new byte[] {(byte) 0x33, (byte) 0x39, (byte) 0x6C}));

    // When
    AppVersionField appVersionField = appVersionFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(39, appVersionField.getVersion());
    Assert.assertEquals('l', appVersionField.getSubVersion());
  }

}
