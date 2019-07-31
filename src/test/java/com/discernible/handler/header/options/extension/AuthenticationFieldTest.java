package com.discernible.handler.header.options.extension;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.message.header.options.extension.AuthenticationField;

public class AuthenticationFieldTest {

  private final AuthenticationFieldHandler authenticationFieldHandler = new AuthenticationFieldHandler();

  @Test
  public void test_encode() {

    // Given
    AuthenticationField authenticationField = new AuthenticationField(AuthenticationField.AuthenticationSubField.FORWARD);
    ByteOutputStream output = new ByteOutputStream();

    // When
    authenticationFieldHandler.encode(authenticationField, output);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, 0x03}, output.toByteArray());
  }

  @Test
  public void test_decode() {

    // Given
    byte[] bytes = new byte[] {(byte) 0x01, (byte) 0x03};
    ByteInputStream in = new ByteInputStream(bytes);

    // When
    AuthenticationField authenticationField = authenticationFieldHandler.decode(in);

    // Then
    Assert.assertEquals(AuthenticationField.AuthenticationSubField.FORWARD, authenticationField.getAuthenticationSubField());

  }

}
