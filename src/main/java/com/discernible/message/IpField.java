package com.discernible.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpField implements Field {

  private static final Pattern IP_REGEX = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");

  private final int firstOctet;
  private final int secondOctet;
  private final int thirdOctet;
  private final int fourthOctet;

  public IpField(String ip) {

    Matcher ipMatcher = IP_REGEX.matcher(ip);

    if (!ipMatcher.find()) {
      throw new IllegalArgumentException(String.format("Provided IP is not in correct format (xxx.xxx.xxx.xxx): %s", ip));
    }

    firstOctet = Integer.parseInt(ipMatcher.group(1));
    secondOctet = Integer.parseInt(ipMatcher.group(2));
    thirdOctet = Integer.parseInt(ipMatcher.group(3));
    fourthOctet = Integer.parseInt(ipMatcher.group(4));
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

}
