package com.discernible.handler.header.options.extension;

import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.AuthenticationField;
import com.discernible.message.header.options.extension.AuthenticationField.AuthenticationSubField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationFieldHandler implements FieldHandler<AuthenticationField> {

  @Override
  public AuthenticationField decode(Queue<Byte> messageBytes) {
    messageBytes.poll(); // Throw away the first byte as we don't need it.

    AuthenticationSubField authenticationSubField = AuthenticationSubField.values()[messageBytes.poll()];

    return new AuthenticationField(authenticationSubField);
  }

  @Override
  public byte[] encode(AuthenticationField authenticationField) {
    byte[] messageBytes = new byte[2];
    messageBytes[0] = 0x01; // HMAC-MD5
    messageBytes[1] = (byte) authenticationField.getAuthenticationSubField().ordinal();

    return messageBytes;
  }

}
