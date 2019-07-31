package com.discernible.message.body.type2;

import java.util.EnumSet;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type2.FixStatusFieldHandler;

public class FixStatusFieldTest {

  // Class under test.
  private FixStatusFieldHandler fixStatusFieldHandler = new FixStatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    FixStatusField fixStatusField = new FixStatusField(EnumSet.of(FixStatusField.FixStatus._2D_FIX));
    ByteOutputStream out = new ByteOutputStream();

    // When
    fixStatusFieldHandler.encode(fixStatusField, out);
    byte[] actualBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {0b00010000,}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0b00010000};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    FixStatusField fixStatusField = fixStatusFieldHandler.decode(in);

    // Then
    Assert.assertEquals(EnumSet.of(FixStatusField.FixStatus._2D_FIX), fixStatusField.getFixStatus());
  }

}
