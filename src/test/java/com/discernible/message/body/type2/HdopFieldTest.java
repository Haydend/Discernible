package com.discernible.message.body.type2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class HdopFieldTest {

  @Test
  public void test_encode() {

    // Given
    HdopField hdopField = new HdopField(0.2);

    // When
    byte[] fieldBytes = hdopField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0x02}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x04));

    // When
    HdopField actualHdopField = HdopField.decode(bytes);

    // Then
    Assert.assertEquals(0.4, actualHdopField.getValue(), 0.01);
  }

}
