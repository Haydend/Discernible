package com.discernible.message.body.type2;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type2.CommStatusFieldHandler;
import com.discernible.message.body.type2.CommStatusField.NetworkTechnology;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class CommStatusFieldTest {

  // Class under test.
  private CommStatusFieldHandler commStatusFieldHandler = new CommStatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    CommStatusField commStatusField = new CommStatusField(true, false, true, false, true, false, NetworkTechnology._2G_NETWORK);

    // When
    byte[] fieldBytes = commStatusFieldHandler.encode(commStatusField);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b00010101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0b00010101}));

    // When
    CommStatusField actualCommStatusField = commStatusFieldHandler.decode(bytes);

    // Then
    CommStatusField expectedCommStatusField = new CommStatusField(true, false, true, false, true, false, NetworkTechnology._2G_NETWORK);
    Assert.assertEquals(expectedCommStatusField, actualCommStatusField);
  }

}
