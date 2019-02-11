package com.discernible.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class SignedIntegerFieldTest {

  @Test
  public void test_encode() {

    // Given
    SignedIntegerField byteField = new SignedIntegerField(4);

    // When
    byte[] actualBytes = byteField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x00, 0x00, 0x00, 0x04 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04));

    // When
    SignedIntegerField integerField = SignedIntegerField.decode(bytes);

    // Then
    Assert.assertEquals(4, integerField.getValue());
  }

}
