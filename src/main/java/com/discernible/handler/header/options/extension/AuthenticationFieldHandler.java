package com.discernible.handler.header.options.extension;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.AuthenticationField;
import com.discernible.message.header.options.extension.AuthenticationField.AuthenticationSubField;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationFieldHandler implements FieldHandler<AuthenticationField> {

  @Override
  public AuthenticationField decode(ByteInputStream in) {
    in.read(); // Throw away the first byte as we don't need it.

    AuthenticationSubField authenticationSubField = AuthenticationSubField.values()[in.read()];

    return new AuthenticationField(authenticationSubField);
  }

  @Override
  public void encode(AuthenticationField authenticationField, ByteOutputStream out) {
    out.write(0x01); // HMAC-MD5
    out.write(authenticationField.getAuthenticationSubField().ordinal());
  }

}
