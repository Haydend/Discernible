package com.discernible.handler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class SignedShortFieldTest {

  private final SignedShortFieldHandler fieldHandler = new SignedShortFieldHandler();

  @Test
  public void test_encode() {

    // Given
    short value = 4;
    ByteOutputStream out = new ByteOutputStream();

    // When
    fieldHandler.encode(value, out);

    // Then
    Assert.assertArrayEquals(new byte[] {0x00, 0x04}, out.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x00, (byte) 0x04));

    // When
    short signedShortField = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(4, signedShortField);
  }

}
