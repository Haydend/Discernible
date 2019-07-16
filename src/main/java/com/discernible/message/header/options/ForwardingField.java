package com.discernible.message.header.options;

import com.discernible.message.IP;

import lombok.Data;

@Data
public class ForwardingField {

  private IP ip;
  private Integer port;
  private Protocol forwardingProtocol;
  private ForwardingOperationType forwardingOperationType;

  public ForwardingField(String ip, Integer port, Protocol forwardingProtocol, ForwardingOperationType forwardingOperationType) {
    this.ip = new IP(ip);
    this.port = port;
    this.forwardingProtocol = forwardingProtocol;
    this.forwardingOperationType = forwardingOperationType;
  }

  public ForwardingField(IP ipField, Integer portField, Protocol protocol,
      ForwardingOperationType forwardingOperationType) {
    this.ip = ipField;
    this.port = portField;
    this.forwardingProtocol = protocol;
    this.forwardingOperationType = forwardingOperationType;
  }

}
