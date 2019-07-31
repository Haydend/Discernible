package com.discernible.message.body.type2;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type2.InputFieldHandler;

public class InputFieldTest {

  // Class under test.
  private InputFieldHandler inputFieldHandler = new InputFieldHandler();

  @Test
  public void test_encode() {

    // Given
    InputField inputField = new InputField(true, false, true, false, true, false, true, false);
    ByteOutputStream out = new ByteOutputStream();

    // When
    inputFieldHandler.encode(inputField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b01010101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0b01010101};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    InputField actualInputField = inputFieldHandler.decode(in);

    // Then
    InputField expectedInputField = new InputField(true, false, true, false, true, false, true, false);
    Assert.assertEquals(expectedInputField, actualInputField);
  }

}
