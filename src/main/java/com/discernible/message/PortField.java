package com.discernible.message;

import java.math.BigInteger;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PortField implements Field {

  private final int port;

  @Override
  public byte[] encode() {

    byte[] messageBytes = new byte[2];

    byte[] portBytes = BigInteger.valueOf(port).toByteArray();
    System.arraycopy(portBytes, 0, messageBytes, 0, 2);

    return messageBytes;
  }

}
