package com.discernible.message.body.type2;

import java.io.ByteArrayInputStream;
import java.util.EnumSet;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type2.FixStatusFieldHandler;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class FixStatusFieldTest {

  // Class under test.
  private FixStatusFieldHandler fixStatusFieldHandler = new FixStatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    FixStatusField fixStatusField = new FixStatusField(EnumSet.of(FixStatusField.FixStatus._2D_FIX));

    // When
    byte[] actualBytes = fixStatusFieldHandler.encode(fixStatusField);

    // Then
    Assert.assertArrayEquals(new byte[] {0b00010000,}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0b00010000}));

    // When
    FixStatusField fixStatusField = fixStatusFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(EnumSet.of(FixStatusField.FixStatus._2D_FIX), fixStatusField.getFixStatus());
  }

}
