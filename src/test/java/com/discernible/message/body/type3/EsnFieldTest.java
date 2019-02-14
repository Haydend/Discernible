package com.discernible.message.body.type3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class EsnFieldTest {

  @Test
  public void test_encode() {

    // Given
    EsnField esnField = new EsnField("4532459871");

    // When
    byte[] fieldBytes = esnField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] {0x00, 0x00, 0x00, 0x45, 0x32, 0x45, (byte) 0x98, 0x71}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes =
        new LinkedList<Byte>(Arrays.asList((byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x45, (byte) 0x32, (byte) 0x45, (byte) 0x98, (byte) 0x71));

    // When
    EsnField field = EsnField.decode(bytes);

    // Then
    Assert.assertEquals("4532459871", field.getEsn());
  }


}
