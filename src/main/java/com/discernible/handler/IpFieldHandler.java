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
  public void encode(IP ip, ByteOutputStream out) {
    out.write(ip.getFirstOctet());
    out.write(ip.getSecondOctet());
    out.write(ip.getThirdOctet());
    out.write(ip.getFourthOctet());
  }

}
