package com.discernible.message.body.type2;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

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
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b00010000));

    // When
    FixStatusField fixStatusField = fixStatusFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(EnumSet.of(FixStatusField.FixStatus._2D_FIX), fixStatusField.getFixStatus());
  }

}
