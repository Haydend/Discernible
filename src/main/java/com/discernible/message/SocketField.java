package com.discernible.message;

import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class SocketField implements Field {

  private final IpField ip;
  private final UnsignedIntegerField port;

  public SocketField(String ip, Integer port) {
    this.ip = new IpField(ip);
    this.port = new UnsignedIntegerField(port);
  }

  private SocketField(IpField ip, UnsignedIntegerField port) {
    this.ip = ip;
    this.port = port;
  }

  public static SocketField decode(Queue<Byte> messageBytes) {

    ByteUtils.getFieldLength(messageBytes); // We don't need the field length, but we need to take the byte off the queue.

    IpField ipField = IpField.decode(messageBytes);
    UnsignedIntegerField portField = UnsignedIntegerField.decode(messageBytes);

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

  public String getIp() {
    return ip.getIP();
  }

  public void setIp(String ip) {
    this.ip.setIp(ip);
  }

  public int getPort() {
    return port.getValue();
  }

  public void setPort(int port) {
    this.port.setValue(port);
  }

}
