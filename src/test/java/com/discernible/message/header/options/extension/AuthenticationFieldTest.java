package com.discernible.message.header.options.extension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

public class AuthenticationFieldTest {

  @Test
  public void test_encode() {

    // Given
    AuthenticationField authenticationField = new AuthenticationField(AuthenticationField.AuthenticationSubField.FORWARD);

    // When
    byte[] actualBytes = authenticationField.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { 0x01, 0x03 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x01, (byte) 0x03));

    // When
    AuthenticationField authenticationField = AuthenticationField.decode(bytes);

    // Then
    Assert.assertEquals(AuthenticationField.AuthenticationSubField.FORWARD, authenticationField.getAuthenticationSubField());

  }

}
