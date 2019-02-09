package com.discernible.message;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WithLengthTest {

  @Spy
  private Field field;

  @Test
  public void test_encode() {

    // Given
    byte[] fieldBytes = new byte[] { 0x01, 0x02, 0x03 };
    Mockito.when(field.encode()).thenReturn(fieldBytes);

    // When
    byte[] actualMessageBytes =field.withLength().encode();

    // Then
    byte[] expectedMessageBytes = new byte[] { 0x03, 0x01, 0x02, 0x03 };
    Assert.assertArrayEquals(expectedMessageBytes, actualMessageBytes);
  }

}
