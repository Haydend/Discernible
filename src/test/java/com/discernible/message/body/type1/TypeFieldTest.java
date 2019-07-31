package com.discernible.message.body.type1;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.body.type1.TypeFieldHandler;
import com.discernible.message.body.Message.MessageType;

public class TypeFieldTest {

  private TypeFieldHandler typeFieldHandler = new TypeFieldHandler();

  @Test
  public void test_encode() {

    // Given
    TypeField statusField = new TypeField(MessageType.ACK_NAK_MESSAGE);
    ByteOutputStream out = new ByteOutputStream();

    // When
    typeFieldHandler.encode(statusField, out);
    byte[] fieldBytes = out.toByteArray();

    // Then
    Assert.assertArrayEquals(new byte[] {0x01}, fieldBytes);
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x01};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    TypeField typeField = typeFieldHandler.decode(in);

    // Then
    Assert.assertEquals(MessageType.ACK_NAK_MESSAGE, typeField.getType());
  }

}
