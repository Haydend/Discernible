package com.discernible.message.body.type2;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type2.CoordinateFieldHandler;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class CoordinateFieldTest {

  // Class under test.
  private final CoordinateFieldHandler coordinateFieldHandler = new CoordinateFieldHandler();

  @Test
  public void test_encode() {

    // Given
    CoordinateField byteField = new CoordinateField(1.1234567);

    // When
    byte[] actualBytes = coordinateFieldHandler.encode(byteField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x00, (byte) 0xAB, 0x6D, 0x07}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x00, (byte) 0xAB, (byte) 0x6D, (byte) 0x07}));

    // When
    CoordinateField coordinateField = coordinateFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(1.1234567, coordinateField.getValue(), 0.00000001);
  }

}
