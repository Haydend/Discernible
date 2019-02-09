package com.discernible.message;

public class SocketField implements Field {

  private final IpField ip;
  private final PortField port;

  public SocketField(String ip, Integer port) {
    this.ip = new IpField(ip);
    this.port = new PortField(port);
  }

  @Override
  public byte[] encode() {
    byte[] messageBytes = new byte[6];

    byte[] ipBytes = ip.encode();
    System.arraycopy(ipBytes, 0, messageBytes, 0, ipBytes.length);

    byte[] portBytes = port.encode();
    System.arraycopy(portBytes, 0, messageBytes, 4, portBytes.length);

    return messageBytes;
  }

}
