package com.discernible.handler;

import com.discernible.message.IP;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class IpFieldHandler implements FieldHandler<IP> {

  public IP decode(JBBPBitInputStream messageBytes) {

    int firstOctet = ByteUtils.getByte(messageBytes) & 0xFF; // 'AND' the byte value to get the unsigned value.
    int secondOctet = ByteUtils.getByte(messageBytes) & 0xFF;
    int thirdOctet = ByteUtils.getByte(messageBytes) & 0xFF;
    int fourthOctet = ByteUtils.getByte(messageBytes) & 0xFF;

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
