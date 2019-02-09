package com.discernible.message.header.options.extension;

import com.discernible.message.Field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthenticationField implements Field {

	private final AuthenticationSubField authenticationSubField;

	@Override
	public byte[] encode() {

		byte[] messageBytes = new byte[2];
		messageBytes[0] = 0x01; // HMAC-MD5
		messageBytes[1] = (byte) authenticationSubField.ordinal();

		return messageBytes;
	}

	public enum AuthenticationSubField {
		MAINTENANCE, SERVICES, INBOUND, FORWARD;
	}

}
