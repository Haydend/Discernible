package com.discernible.message.body.type2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.body.type2.UnitStatusField.Status;

public class UnitStatusFieldTest {

  @Test
  public void test_encode() {

    // Given
    UnitStatusField inputField = new UnitStatusField(Status.OK, Status.ERROR, Status.OK, true);

    // When
    byte[] fieldBytes = inputField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b00001101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b00001101));

    // When
    UnitStatusField actualUnitStatusField = UnitStatusField.decode(bytes);

    // Then
    UnitStatusField expectedInputField = new UnitStatusField(Status.OK, Status.ERROR, Status.OK, true);
    Assert.assertEquals(expectedInputField, actualUnitStatusField);
  }

}
