package com.discernible.message.body.type2;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type2.InputFieldHandler;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class InputFieldTest {

  // Class under test.
  private InputFieldHandler inputFieldHandler = new InputFieldHandler();

  @Test
  public void test_encode() {

    // Given
    InputField inputField = new InputField(true, false, true, false, true, false, true, false);

    // When
    byte[] fieldBytes = inputFieldHandler.encode(inputField);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b01010101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0b01010101}));

    // When
    InputField actualInputField = inputFieldHandler.decode(bytes);

    // Then
    InputField expectedInputField = new InputField(true, false, true, false, true, false, true, false);
    Assert.assertEquals(expectedInputField, actualInputField);
  }

}
