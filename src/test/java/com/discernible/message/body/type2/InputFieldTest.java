package com.discernible.message.body.type2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class InputFieldTest {

  @Test
  public void test_encode() {

    // Given
    InputField inputField = new InputField(true, false, true, false, true, false, true, false);

    // When
    byte[] fieldBytes = inputField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b01010101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b01010101));

    // When
    InputField actualInputField = InputField.decode(bytes);

    // Then
    InputField expectedInputField = new InputField(true, false, true, false, true, false, true, false);
    Assert.assertEquals(expectedInputField, actualInputField);
  }

}
