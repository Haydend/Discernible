package com.discernible.message.body.type2;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type2.CoordinateFieldHandler;

public class CoordinateFieldTest {

  // Class under test.
  private final CoordinateFieldHandler coordinateFieldHandler = new CoordinateFieldHandler();

  @Test
  public void test_encode() {

    // Given
    CoordinateField byteField = new CoordinateField(1.1234567);
    ByteOutputStream out = new ByteOutputStream();

    // When
    coordinateFieldHandler.encode(byteField, out);
    byte[] actualBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {0x00, (byte) 0xAB, 0x6D, 0x07}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x00, (byte) 0xAB, (byte) 0x6D, (byte) 0x07};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    CoordinateField coordinateField = coordinateFieldHandler.decode(in);

    // Then
    Assert.assertEquals(1.1234567, coordinateField.getValue(), 0.00000001);
  }

}
