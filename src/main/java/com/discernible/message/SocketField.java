package com.discernible.message;

import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class SocketField implements Field {

  private final IpField ip;
  private final PortField port;

  public SocketField(String ip, Integer port) {
    this.ip = new IpField(ip);
    this.port = new PortField(port);
  }

  private SocketField(IpField ip, PortField port) {
    this.ip = ip;
    this.port = port;
  }

  public static SocketField decode(Queue<Byte> messageBytes) {

    ByteUtils.getFieldLength(messageBytes); // We don't need the field length, but we need to take the byte off the queue.

    IpField ipField = IpField.decode(messageBytes);
    PortField portField = PortField.decode(messageBytes);

    return new SocketField(ipField, portField);
  }

  @Override
  public byte[] encode() {
    byte[] messageBytes = new byte[6];

    byte[] ipBytes = ip.encode();
    System.arraycopy(ipBytes, 0, messageBytes, 0, ipBytes.length);

    byte[] portBytes = port.encode();
    System.arraycopy(portBytes, 0, messageBytes, 4, portBytes.length);

    return ByteUtils.prependFieldLength(messageBytes);
  }

  public String getIP() {
    return ip.getIP();
  }

  public void setIP(String ip) {
    this.ip.setIp(ip);
  }

  public int getPort() {
    return port.getPort();
  }

  public void setPort(int port) {
    this.port.setPort(port);
  }

}
