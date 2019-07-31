package com.discernible.handler.header.options.extension;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.EncryptionField.EncryptionSubField;

public class EncryptionFieldTest {

  private final EncryptionFieldHandler encryptionFieldHandler = new EncryptionFieldHandler();

  @Test
  public void test_encode() {

    // Given
    EncryptionField encryptionField = new EncryptionField(EncryptionSubField.ESN, 1234567);
    ByteOutputStream output = new ByteOutputStream();

    // When
    encryptionFieldHandler.encode(encryptionField, output);

    // Then
    Assert.assertArrayEquals(new byte[] {0x06, 0x01, 0x01, 0x00, 0x12, (byte) 0xD6, (byte) 0x87}, output.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x06, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x12, (byte) 0xD6, (byte) 0x87};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    EncryptionField encryptionField = encryptionFieldHandler.decode(in);

    // Then
    Assert.assertEquals(EncryptionField.EncryptionSubField.ESN, encryptionField.getEncryptionSubField());
    Assert.assertEquals(1234567, encryptionField.getRandomKey());
  }

}
