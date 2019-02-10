package com.discernible.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class ByteFieldTest {

  @Test
  public void test_encode() {

    // Given
    ByteField byteField = new ByteField(new byte[] { 0x02, 0x03, 0x04 });

    // When
    byte[] actualBytes = byteField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x03, 0x02, 0x03, 0x04 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x01));

    // When
    ByteField byteField = ByteField.decode(bytes);

    // Then
    Assert.assertArrayEquals(new byte[] { 0x01, 0x02 }, byteField.getField());
  }

}
