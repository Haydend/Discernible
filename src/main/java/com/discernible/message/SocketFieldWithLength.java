package com.discernible.message;

public class SocketFieldWithLength extends FieldWithLength {

  private final IpField ip;
  private final PortField port;

  public SocketFieldWithLength(String ip, Integer port) {
    this.ip = new IpField(ip);
    this.port = new PortField(port);
  }

  @Override
  protected byte[] encodeField() {
    byte[] messageBytes = new byte[6];

    byte[] ipBytes = ip.encode();
    System.arraycopy(ipBytes, 0, messageBytes, 0, ipBytes.length);

    byte[] portBytes = port.encode();
    System.arraycopy(portBytes, 0, messageBytes, 4, portBytes.length);

    return messageBytes;
  }

}
