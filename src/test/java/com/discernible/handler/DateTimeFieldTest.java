package com.discernible.handler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class DateTimeFieldTest {

  private final DateTimeFieldHandler fieldHandler = new DateTimeFieldHandler();

  @Test
  public void test_encode() {

    // Given
    LocalDateTime dateTimeField = LocalDateTime.of(1970, 01, 1, 1, 0);

    // When
    byte[] dateTimeFieldBytes = fieldHandler.encode(dateTimeField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x00, 0x00, 0x0E, 0x10}, dateTimeFieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x00, (byte) 0x00, (byte) 0x0E, (byte) 0x10));

    // When
    LocalDateTime dateTimeField = fieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(LocalDateTime.of(1970, 1, 1, 1, 0), dateTimeField);
  }

}
