package com.discernible.message.header.options.extension;

import java.util.Queue;

import com.discernible.message.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationField implements Field {

  private AuthenticationSubField authenticationSubField;

  public static AuthenticationField decode(Queue<Byte> messageBytes) {

    messageBytes.poll(); // Throw away the first byte as we don't need it.

    AuthenticationSubField authenticationSubField = AuthenticationSubField.values()[messageBytes.poll()];

    return new AuthenticationField(authenticationSubField);
  }

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
