package com.discernible.message.body.type1;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
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
    byte[] bytes = new byte[] {(byte) 0x0A};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    StatusField statusField = statusFieldHandler.decode(in);

    // Then
    Assert.assertEquals(Status.FAILED_PARAMETER_UPDATE_FAILURE, statusField.getStatus());
  }

}
