package com.discernible.message.body;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.UnitStatusFieldHandler;
import com.discernible.message.body.UnitStatusField.Status;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class UnitStatusFieldTest {

  private UnitStatusFieldHandler unitStatusFieldHandler = new UnitStatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    UnitStatusField inputField = new UnitStatusField(Status.OK, Status.ERROR, Status.OK, true);

    // When
    byte[] fieldBytes = unitStatusFieldHandler.encode(inputField);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b00001101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(new ByteArrayInputStream(
        new byte[] {(byte) 0b00001101}));

    // When
    UnitStatusField actualUnitStatusField = unitStatusFieldHandler.decode(bytes);

    // Then
    UnitStatusField expectedInputField = new UnitStatusField(Status.OK, Status.ERROR, Status.OK, true);
    Assert.assertEquals(expectedInputField, actualUnitStatusField);
  }

}
