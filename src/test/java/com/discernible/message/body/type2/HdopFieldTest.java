package com.discernible.message.body.type2;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type2.HdopFieldHandler;

public class HdopFieldTest {

  // Class under test.
  private HdopFieldHandler hdopFieldHandler = new HdopFieldHandler();

  @Test
  public void test_encode() {

    // Given
    HdopField hdopField = new HdopField(0.2);
    ByteOutputStream out = new ByteOutputStream();

    // When
    hdopFieldHandler.encode(hdopField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0x02}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x04};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    HdopField actualHdopField = hdopFieldHandler.decode(in);

    // Then
    Assert.assertEquals(0.4, actualHdopField.getValue(), 0.01);
  }

}
