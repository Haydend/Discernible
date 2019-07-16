package com.discernible.message.body.type1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type1.TypeFieldHandler;
import com.discernible.message.body.Message.MessageType;

public class TypeFieldTest {

  private TypeFieldHandler typeFieldHandler = new TypeFieldHandler();

  @Test
  public void test_encode() {

    // Given
    TypeField statusField = new TypeField(MessageType.ACK_NAK_MESSAGE);

    // When
    byte[] fieldBytes = typeFieldHandler.encode(statusField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x01));

    // When
    TypeField typeField = typeFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(MessageType.ACK_NAK_MESSAGE, typeField.getType());
  }

}
