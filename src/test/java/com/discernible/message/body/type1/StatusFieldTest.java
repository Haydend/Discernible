package com.discernible.message.body.type1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type1.StatusFieldHandler;
import com.discernible.message.body.type1.StatusField.Status;

public class StatusFieldTest {

  private StatusFieldHandler statusFieldHandler = new StatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    StatusField statusField = new StatusField(Status.FAILED_PARAMETER_UPDATE_FAILURE);
    ByteOutputStream out = new ByteOutputStream();

    // When
    statusFieldHandler.encode(statusField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {0x0A}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x0A));

    // When
    StatusField statusField = statusFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(Status.FAILED_PARAMETER_UPDATE_FAILURE, statusField.getStatus());
  }

}
