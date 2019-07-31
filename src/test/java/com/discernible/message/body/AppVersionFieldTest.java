package com.discernible.message.body;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.AppVersionFieldHandler;

public class AppVersionFieldTest {

  private AppVersionFieldHandler appVersionFieldHandler = new AppVersionFieldHandler();

  @Test
  public void test_encode() {

    // Given
    AppVersionField appVersionField = new AppVersionField(39, 'l');
    ByteOutputStream out = new ByteOutputStream();

    // When
    appVersionFieldHandler.encode(appVersionField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {0x33, 0x39, 0x6C}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x33, (byte) 0x39, (byte) 0x6C};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    AppVersionField appVersionField = appVersionFieldHandler.decode(in);

    // Then
    Assert.assertEquals(39, appVersionField.getVersion());
    Assert.assertEquals('l', appVersionField.getSubVersion());
  }

}
