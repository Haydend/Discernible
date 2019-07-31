package com.discernible.message.body;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.UnitStatusFieldHandler;
import com.discernible.message.body.UnitStatusField.Status;

public class UnitStatusFieldTest {

  private UnitStatusFieldHandler unitStatusFieldHandler = new UnitStatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    UnitStatusField inputField = new UnitStatusField(Status.OK, Status.ERROR, Status.OK, true);
    ByteOutputStream out = new ByteOutputStream();

    // When
    unitStatusFieldHandler.encode(inputField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b00001101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0b00001101};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    UnitStatusField actualUnitStatusField = unitStatusFieldHandler.decode(in);

    // Then
    UnitStatusField expectedInputField = new UnitStatusField(Status.OK, Status.ERROR, Status.OK, true);
    Assert.assertEquals(expectedInputField, actualUnitStatusField);
  }

}
