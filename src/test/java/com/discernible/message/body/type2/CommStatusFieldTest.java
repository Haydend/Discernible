package com.discernible.message.body.type2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type2.CommStatusFieldHandler;
import com.discernible.message.body.type2.CommStatusField.NetworkTechnology;

public class CommStatusFieldTest {

  // Class under test.
  private CommStatusFieldHandler commStatusFieldHandler = new CommStatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    CommStatusField commStatusField = new CommStatusField(true, false, true, false, true, false, NetworkTechnology._2G_NETWORK);

    // When
    byte[] fieldBytes = commStatusFieldHandler.encode(commStatusField);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b00010101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b00010101));

    // When
    CommStatusField actualCommStatusField = commStatusFieldHandler.decode(bytes);

    // Then
    CommStatusField expectedCommStatusField = new CommStatusField(true, false, true, false, true, false, NetworkTechnology._2G_NETWORK);
    Assert.assertEquals(expectedCommStatusField, actualCommStatusField);
  }

}
