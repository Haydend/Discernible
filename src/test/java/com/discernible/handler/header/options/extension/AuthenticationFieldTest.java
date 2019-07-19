package com.discernible.handler.header.options.extension;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.header.options.extension.AuthenticationField;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class AuthenticationFieldTest {

  private final AuthenticationFieldHandler authenticationFieldHandler = new AuthenticationFieldHandler();

  @Test
  public void test_encode() {

    // Given
    AuthenticationField authenticationField = new AuthenticationField(AuthenticationField.AuthenticationSubField.FORWARD);

    // When
    byte[] actualBytes = authenticationFieldHandler.encode(authenticationField);

    // Then
    Assert.assertArrayEquals(new byte[] {0x01, 0x03}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(new ByteArrayInputStream(new byte[] {(byte) 0x01, (byte) 0x03}));

    // When
    AuthenticationField authenticationField = authenticationFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(AuthenticationField.AuthenticationSubField.FORWARD, authenticationField.getAuthenticationSubField());

  }

}
