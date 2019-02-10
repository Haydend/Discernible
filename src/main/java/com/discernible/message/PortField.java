package com.discernible.message;

import java.math.BigInteger;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PortField implements Field {

  private int port;

  public PortField(int port) {
    this.port = port;
  }

  @Override
  public byte[] encode() {
    byte[] messageBytes = new byte[2];

    byte[] portBytes = BigInteger.valueOf(port).toByteArray();
    System.arraycopy(portBytes, 0, messageBytes, 0, 2);

    return messageBytes;
  }

  public static PortField decode(Queue<Byte> messageBytes) {
    byte[] portBytes = new byte[] { messageBytes.poll(), messageBytes.poll() };
    int port = ByteUtils.unsignedShortToint(portBytes);

    return new PortField(port);
  }

}
