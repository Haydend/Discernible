package com.discernible.message.body.type2;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type2.CommStatusFieldHandler;
import com.discernible.message.body.type2.CommStatusField.NetworkTechnology;

public class CommStatusFieldTest {

  // Class under test.
  private CommStatusFieldHandler commStatusFieldHandler = new CommStatusFieldHandler();

  @Test
  public void test_encode() {

    // Given
    CommStatusField commStatusField = new CommStatusField(true, false, true, false, true, false, NetworkTechnology._2G_NETWORK);
    ByteOutputStream out = new ByteOutputStream();

    // When
    commStatusFieldHandler.encode(commStatusField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b00010101}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0b00010101};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    CommStatusField actualCommStatusField = commStatusFieldHandler.decode(in);

    // Then
    CommStatusField expectedCommStatusField = new CommStatusField(true, false, true, false, true, false, NetworkTechnology._2G_NETWORK);
    Assert.assertEquals(expectedCommStatusField, actualCommStatusField);
  }

}
