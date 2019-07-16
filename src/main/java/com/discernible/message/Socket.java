package com.discernible.message;

public class Socket {

  private final IP ip;
  private int port;

  public Socket(String ip, int port) {
    this.ip = new IP(ip);
    this.port = port;
  }

  public Socket(IP ip, int port) {
    this.ip = ip;
    this.port = port;
  }

  public String getIpText() {
    return ip.getIP();
  }

  public IP getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip.setIp(ip);
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

}
