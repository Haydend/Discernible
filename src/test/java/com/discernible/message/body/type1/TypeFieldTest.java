package com.discernible.message.body.type1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.body.MessageBody.MessageType;

public class TypeFieldTest {

  @Test
  public void test_encode() {

    // Given
    TypeField statusField = new TypeField(MessageType.ACK_NAK_MESSAGE);

    // When
    byte[] fieldBytes = statusField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x01 }, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x01));

    // When
    TypeField typeField = TypeField.decode(bytes);

    // Then
    Assert.assertEquals(MessageType.ACK_NAK_MESSAGE, typeField.getType());
  }

}
