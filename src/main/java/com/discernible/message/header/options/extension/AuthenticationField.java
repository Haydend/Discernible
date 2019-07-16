package com.discernible.message.header.options.extension;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationField {

  private AuthenticationSubField authenticationSubField;

  public enum AuthenticationSubField {
    MAINTENANCE,
    SERVICES,
    INBOUND,
    FORWARD;
  }

}
