package com.discernible.message;

import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class IpField implements Field {

  private static final Pattern IP_REGEX = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");

  private int firstOctet;
  private int secondOctet;
  private int thirdOctet;
  private int fourthOctet;

  public IpField(String ip) {
    setIp(ip);
  }

  private IpField(int firstOctet, int secondOctet, int thirdOctet, int fourthOctet) {
    this.firstOctet = firstOctet;
    this.secondOctet = secondOctet;
    this.thirdOctet = thirdOctet;
    this.fourthOctet = fourthOctet;
  }

  public static IpField decode(Queue<Byte> messageBytes) {

    int firstOctet = messageBytes.poll() & 0xFF; // 'AND' the byte value to get the unsigned value.
    int secondOctet = messageBytes.poll() & 0xFF;
    int thirdOctet = messageBytes.poll() & 0xFF;
    int fourthOctet = messageBytes.poll() & 0xFF;

    return new IpField(firstOctet, secondOctet, thirdOctet, fourthOctet);
  }

  @Override
  public byte[] encode() {

    byte[] messageBytes = new byte[4];
    messageBytes[0] = (byte) firstOctet;
    messageBytes[1] = (byte) secondOctet;
    messageBytes[2] = (byte) thirdOctet;
    messageBytes[3] = (byte) fourthOctet;

    return messageBytes;
  }

  public String getIP() {
    return String.format("%s.%s.%s.%s", firstOctet, secondOctet, thirdOctet, fourthOctet);
  }

  public void setIp(String ip) {
    Matcher ipMatcher = IP_REGEX.matcher(ip);

    if (!ipMatcher.find()) {
      throw new IllegalArgumentException(String.format("Provided IP is not in correct format (xxx.xxx.xxx.xxx): %s", ip));
    }

    firstOctet = Integer.parseInt(ipMatcher.group(1));
    secondOctet = Integer.parseInt(ipMatcher.group(2));
    thirdOctet = Integer.parseInt(ipMatcher.group(3));
    fourthOctet = Integer.parseInt(ipMatcher.group(4));
  }

}
