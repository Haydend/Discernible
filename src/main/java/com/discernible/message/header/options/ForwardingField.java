package com.discernible.message.header.options;

import com.discernible.message.Field;
import com.discernible.message.IpField;
import com.discernible.message.PortField;

import lombok.Data;

@Data
public class ForwardingField implements Field {

  private final IpField ip;
  private final PortField port;
  private final Protocol forwardingProtocol;
  private final ForwardingOperationType forwardingOperationType;

  public ForwardingField(String ip, Integer port, Protocol forwardingProtocol, ForwardingOperationType forwardingOperationType) {
    this.ip = new IpField(ip);
    this.port = new PortField(port);
    this.forwardingProtocol = forwardingProtocol;
    this.forwardingOperationType = forwardingOperationType;
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

    return messageBytes;
  }

  public enum Protocol {
    TCP((byte) 0x06),
    UDP((byte) 0x11);

    private final byte byteForEnum;

    private Protocol(byte byteForEnum) {
      this.byteForEnum = byteForEnum;
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

    public byte encode() {
      return byteForEnum;
    }

  }

}
