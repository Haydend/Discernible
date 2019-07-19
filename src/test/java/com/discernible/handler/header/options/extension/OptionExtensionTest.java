package com.discernible.handler.header.options.extension;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.LmDirectRouting;
import com.discernible.message.header.options.extension.OptionExtension;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class OptionExtensionTest {

  private final OptionExtensionFieldHandler optionExtensionFieldHandler = new OptionExtensionFieldHandler();

  @Test
  public void test_encode_esn() {

    // Given
    byte[] esn = new byte[] {0x01, 0x02, 0x03, 0x04};
    OptionExtension optionExtension = new OptionExtension(esn, null, null, false, null);

    // When
    byte[] actualBytes = optionExtensionFieldHandler.encode(optionExtension);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, (byte) 0b00000001, 0x04, 0x01, 0x02, 0x03, 0x04}, actualBytes);
  }

  @Test
  public void test_decode_esn() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(new byte[] {(byte) 0x01, (byte) 0b00000001, (byte) 0x04, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04}));

    // When
    OptionExtension optionExtension = optionExtensionFieldHandler.decode(bytes);

    // Then
    Assert.assertNotNull(optionExtension.getEsn());
    Assert.assertNull(optionExtension.getVin());
    Assert.assertNull(optionExtension.getEncryption());
    Assert.assertFalse(optionExtension.isLmDirectCompression());
    Assert.assertNull(optionExtension.getLmDirectRouting());

    Assert.assertArrayEquals(new byte[] {0x01, 0x02, 0x03, 0x04}, optionExtension.getEsn());
  }

  @Test
  public void test_encode_vin() {

    // Given
    String vin = "test";
    OptionExtension optionExtension = new OptionExtension(null, vin, null, false, null);

    // When
    byte[] actualBytes = optionExtensionFieldHandler.encode(optionExtension);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, (byte) 0b00000010, 0x04, 0x74, 0x65, 0x73, 0x74}, actualBytes);
  }

  @Test
  public void test_decode_vin() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(new byte[] {(byte) 0x01, (byte) 0b00000010, (byte) 0x04, (byte) 0x74, (byte) 0x65, (byte) 0x73, (byte) 0x74}));

    // When
    OptionExtension optionExtension = optionExtensionFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionExtension.getEsn());
    Assert.assertNotNull(optionExtension.getVin());
    Assert.assertNull(optionExtension.getEncryption());
    Assert.assertFalse(optionExtension.isLmDirectCompression());
    Assert.assertNull(optionExtension.getLmDirectRouting());

    Assert.assertEquals("test", optionExtension.getVin());
  }

  @Test
  public void test_encode_encryption() {

    // Given
    EncryptionField encryptionField = new EncryptionField(EncryptionField.EncryptionSubField.ESN, 1234567);
    OptionExtension optionExtension = new OptionExtension(null, null, encryptionField, false, null);

    // When
    byte[] actualBytes = optionExtensionFieldHandler.encode(optionExtension);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, (byte) 0b00000100, 0x06, 0x01, 0x01, 0x00, 0x12, (byte) 0xD6, (byte) 0x87}, actualBytes);
  }

  @Test
  public void test_decode_encryption() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x01, (byte) 0b00000100, (byte) 0x06, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x12, (byte) 0xD6, (byte) 0x87}));

    // When
    OptionExtension optionExtension = optionExtensionFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionExtension.getEsn());
    Assert.assertNull(optionExtension.getVin());
    Assert.assertNotNull(optionExtension.getEncryption());
    Assert.assertFalse(optionExtension.isLmDirectCompression());
    Assert.assertNull(optionExtension.getLmDirectRouting());

    Assert.assertEquals(EncryptionField.EncryptionSubField.ESN, optionExtension.getEncryption().getEncryptionSubField());
    Assert.assertEquals(1234567, optionExtension.getEncryption().getRandomKey());
  }

  @Test
  public void test_encode_lmDirectCompression() {

    // Given
    OptionExtension optionExtension = new OptionExtension(null, null, null, true, null);

    // When
    byte[] actualBytes = optionExtensionFieldHandler.encode(optionExtension);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, (byte) 0b00001000}, actualBytes);
  }

  @Test
  public void test_decode_lmDirectCompression() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x01, (byte) 0b00001000}));

    // When
    OptionExtension optionExtension = optionExtensionFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionExtension.getEsn());
    Assert.assertNull(optionExtension.getVin());
    Assert.assertNull(optionExtension.getEncryption());
    Assert.assertTrue(optionExtension.isLmDirectCompression());
    Assert.assertNull(optionExtension.getLmDirectRouting());
  }

  @Test
  public void test_encode_lmDirectRouting() {

    // Given
    LmDirectRouting lmDirectRouting = new LmDirectRouting();
    OptionExtension optionExtension = new OptionExtension(null, null, null, false, lmDirectRouting);

    // When
    byte[] actualBytes = optionExtensionFieldHandler.encode(optionExtension);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, (byte) 0b00010000, 0x03, 0x01, 0x00, 0x00}, actualBytes);
  }

  @Test
  public void test_decode_lmDirectRouting() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x01, (byte) 0b00010000, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x00}));

    // When
    OptionExtension optionExtension = optionExtensionFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionExtension.getEsn());
    Assert.assertNull(optionExtension.getVin());
    Assert.assertNull(optionExtension.getEncryption());
    Assert.assertFalse(optionExtension.isLmDirectCompression());
    Assert.assertNotNull(optionExtension.getLmDirectRouting());
  }

}
