package com.discernible.message.header.options;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.EncryptionField.EncryptionSubField;

public class EncryptionFieldTest {

	@Test
	public void test_encode() {

		// Given
		EncryptionField encryptionField = new EncryptionField(EncryptionSubField.ESN, 1234567);

		// When
		byte[] actualMessaeBytes = encryptionField.encode();

		// Then
		Assert.assertArrayEquals(new byte[] { 0x01, 0x01, 0x00, 0x12, (byte) 0xD6, (byte) 0x87 }, actualMessaeBytes);
	}

}
