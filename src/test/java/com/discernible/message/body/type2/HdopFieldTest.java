package com.discernible.message.body.type2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type2.HdopFieldHandler;

public class HdopFieldTest {

  // Class under test.
  private HdopFieldHandler hdopFieldHandler = new HdopFieldHandler();

  @Test
  public void test_encode() {

    // Given
    HdopField hdopField = new HdopField(0.2);
    ByteOutputStream out = new ByteOutputStream();

    // When
    hdopFieldHandler.encode(hdopField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0x02}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x04));

    // When
    HdopField actualHdopField = hdopFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(0.4, actualHdopField.getValue(), 0.01);
  }

}
