package com.discernible.message.body.type2;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type2.HdopFieldHandler;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class HdopFieldTest {

  // Class under test.
  private HdopFieldHandler hdopFieldHandler = new HdopFieldHandler();

  @Test
  public void test_encode() {

    // Given
    HdopField hdopField = new HdopField(0.2);

    // When
    byte[] fieldBytes = hdopFieldHandler.encode(hdopField);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0x02}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x04}));

    // When
    HdopField actualHdopField = hdopFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(0.4, actualHdopField.getValue(), 0.01);
  }

}
