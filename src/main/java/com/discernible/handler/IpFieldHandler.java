package com.discernible.handler;

import java.util.Queue;

import com.discernible.message.IP;

public class IpFieldHandler implements FieldHandler<IP> {

  public IP decode(Queue<Byte> messageBytes) {

    int firstOctet = messageBytes.poll() & 0xFF; // 'AND' the byte value to get the unsigned value.
    int secondOctet = messageBytes.poll() & 0xFF;
    int thirdOctet = messageBytes.poll() & 0xFF;
    int fourthOctet = messageBytes.poll() & 0xFF;

    return new IP(firstOctet, secondOctet, thirdOctet, fourthOctet);
  }

  @Override
  public byte[] encode(IP ip) {

    byte[] messageBytes = new byte[4];
    messageBytes[0] = (byte) ip.getFirstOctet();
    messageBytes[1] = (byte) ip.getSecondOctet();
    messageBytes[2] = (byte) ip.getThirdOctet();
    messageBytes[3] = (byte) ip.getFourthOctet();

    return messageBytes;
  }

}
