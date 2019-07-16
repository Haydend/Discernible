package com.discernible.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;

@Data
public class IP {

  private static final Pattern IP_REGEX = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");

  private int firstOctet;
  private int secondOctet;
  private int thirdOctet;
  private int fourthOctet;

  public IP(String ip) {
    setIp(ip);
  }

  public IP(int firstOctet, int secondOctet, int thirdOctet, int fourthOctet) {
    this.firstOctet = firstOctet;
    this.secondOctet = secondOctet;
    this.thirdOctet = thirdOctet;
    this.fourthOctet = fourthOctet;
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
