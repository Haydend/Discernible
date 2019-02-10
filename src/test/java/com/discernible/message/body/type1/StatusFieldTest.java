package com.discernible.message.body.type1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.body.type1.StatusField.Status;

public class StatusFieldTest {

  @Test
  public void test_encode() {

    // Given
    StatusField statusField = new StatusField(Status.FAILED_PARAMETER_UPDATE_FAILURE);

    // When
    byte[] fieldBytes = statusField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x0A }, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x0A));

    // When
    StatusField statusField = StatusField.decode(bytes);

    // Then
    Assert.assertEquals(Status.FAILED_PARAMETER_UPDATE_FAILURE, statusField.getStatus());
  }

}
