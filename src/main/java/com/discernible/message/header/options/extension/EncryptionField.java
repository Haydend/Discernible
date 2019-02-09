package com.discernible.message.header.options.extension;

import java.math.BigInteger;

import com.discernible.message.Field;

import lombok.Data;

@Data
public class EncryptionField implements Field {

	private final EncryptionSubField encryptionSubField;
	private final int randomKey;

	@Override
	public byte[] encode() {

		byte[] messageBytes = new byte[6];

		messageBytes[0] = 0x01;

		messageBytes[1] = (byte) encryptionSubField.ordinal();

		byte[] randomKeyBytes = BigInteger.valueOf(randomKey).toByteArray();
		int padding = 4 - randomKeyBytes.length;
		System.arraycopy(randomKeyBytes, 0, messageBytes, 2 + padding, randomKeyBytes.length);

		return messageBytes;
	}

	public enum EncryptionSubField {
		NONE, ESN, IMEI_MEID, USER_DEFINED_MOBILE_ID
	}
}
