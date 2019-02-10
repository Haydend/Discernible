package com.discernible.message;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class DateTimeFieldTest {

  @Test
  public void test_encode() {

    // Given
    DateTimeField dateTimeField = new DateTimeField(LocalDateTime.of(1970, 01, 1, 1, 0));

    // When
    byte[] dateTimeFieldBytes = dateTimeField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x00, 0x00, 0x0E, 0x10 }, dateTimeFieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x00, (byte) 0x00, (byte) 0x0E, (byte) 0x10));

    // When
    DateTimeField dateTimeField = DateTimeField.decode(bytes);

    // Then
    Assert.assertEquals(LocalDateTime.of(1970, 1, 1, 1, 0), dateTimeField.getDatetime());
  }

}
