package com.discernible.message.body.type1;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.type1.TypeFieldHandler;
import com.discernible.message.body.Message.MessageType;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

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
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x01}));

    // When
    TypeField typeField = typeFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(MessageType.ACK_NAK_MESSAGE, typeField.getType());
  }

}
