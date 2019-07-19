package com.discernible.message.body.type1;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type1.StatusFieldHandler;
import com.discernible.message.body.type1.StatusField.Status;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class StatusFieldTest {

  private StatusFieldHandler statusFieldHandler = new StatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    StatusField statusField = new StatusField(Status.FAILED_PARAMETER_UPDATE_FAILURE);

    // When
    byte[] fieldBytes = statusFieldHandler.encode(statusField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x0A}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x0A}));

    // When
    StatusField statusField = statusFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(Status.FAILED_PARAMETER_UPDATE_FAILURE, statusField.getStatus());
  }

}
