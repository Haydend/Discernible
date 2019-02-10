package com.discernible.message.header.options;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.message.IpField;
import com.discernible.message.PortField;
import com.discernible.util.ByteUtils;

import lombok.Data;

@Data
public class ForwardingField implements Field {

  private IpField ip;
  private PortField port;
  private Protocol forwardingProtocol;
  private ForwardingOperationType forwardingOperationType;

  public ForwardingField(String ip, Integer port, Protocol forwardingProtocol, ForwardingOperationType forwardingOperationType) {
    this.ip = new IpField(ip);
    this.port = new PortField(port);
    this.forwardingProtocol = forwardingProtocol;
    this.forwardingOperationType = forwardingOperationType;
  }

  private ForwardingField(IpField ipField, PortField portField, Protocol protocol, ForwardingOperationType forwardingOperationType) {
    this.ip = ipField;
    this.port = portField;
    this.forwardingProtocol = protocol;
    this.forwardingOperationType = forwardingOperationType;
  }

  public static ForwardingField decode(Queue<Byte> messageBytes) {

    messageBytes.poll(); // Throw away field length.

    IpField ipField = IpField.decode(messageBytes);
    PortField portField = PortField.decode(messageBytes);
    Protocol forwardingProtocol = Protocol.decode(messageBytes);
    ForwardingOperationType forwardingOperationType = ForwardingOperationType.decode(messageBytes);

    return new ForwardingField(ipField, portField, forwardingProtocol, forwardingOperationType);
  }

  @Override
  public byte[] encode() {

    byte[] messageBytes = new byte[8];

    byte[] ipBytes = ip.encode();
    System.arraycopy(ipBytes, 0, messageBytes, 0, ipBytes.length);

    byte[] portBytes = port.encode();
    System.arraycopy(portBytes, 0, messageBytes, 4, portBytes.length);

    messageBytes[6] = forwardingProtocol.encode();
    messageBytes[7] = forwardingOperationType.encode();

    return ByteUtils.prependFieldLength(messageBytes);
  }

  public void setIp(String ip) {
    this.ip.setIp(ip);
  }

  public String getIp() {
    return this.ip.getIP();
  }

  public void setPort(int port) {
    this.port.setPort(port);
  }

  public int getPort() {
    return this.port.getPort();
  }

  public enum Protocol {
    TCP((byte) 0x06),
    UDP((byte) 0x11);

    private final byte byteForEnum;

    private Protocol(byte byteForEnum) {
      this.byteForEnum = byteForEnum;
    }

    public static Protocol decode(Queue<Byte> messageBytes) {
      byte byteToMatch = messageBytes.poll();
      for (Protocol protocol : Protocol.values()) {
        if (protocol.byteForEnum == byteToMatch) {
          return protocol;
        }
      }

      throw new IllegalArgumentException(String.format("No Protocol found for byte: %s", byteToMatch));
    }

    public byte encode() {
      return byteForEnum;
    }
  }

  public enum ForwardingOperationType {
    FORWARD((byte) 0x00),
    PROXY((byte) 0x01),
    FORWARD_WITH_LOOKUP((byte) 0x02);

    private final byte byteForEnum;

    private ForwardingOperationType(byte byteForEnum) {
      this.byteForEnum = byteForEnum;
    }

    public static ForwardingOperationType decode(Queue<Byte> messageBytes) {
      byte byteToMatch = messageBytes.poll();
      for (ForwardingOperationType forwardingOperationType : ForwardingOperationType.values()) {
        if (forwardingOperationType.byteForEnum == byteToMatch) {
          return forwardingOperationType;
        }
      }

      throw new IllegalArgumentException(String.format("No Forwarding Operation Type found for byte: %s", byteToMatch));
    }

    public byte encode() {
      return byteForEnum;
    }

  }

}
