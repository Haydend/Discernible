package com.discernible.handler;

import com.discernible.message.IP;

public class IpFieldHandler implements FieldHandler<IP> {

  public IP decode(ByteInputStream in) {

    int firstOctet = in.read() & 0xFF; // 'AND' the byte value to get the unsigned value.
    int secondOctet = in.read() & 0xFF;
    int thirdOctet = in.read() & 0xFF;
    int fourthOctet = in.read() & 0xFF;

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
