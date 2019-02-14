package com.discernible.message.body.type3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.discernible.message.Message;

public class IdReportMessageTest {

  @Test
  public void test_encode() {

  }

  @Test
  public void test_decode() {

    // Given
    String hexString =
        "8308359316075177508f010201030001c82033396c000823c302000000004532459871ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff4f54413a317c303b302c312c392c31317c373b30004f5441535441543a313534383935303433332c302c312c352c302c2222004654424c3a302c312c3743334200";
    byte[] data = hexStringToByteArray(hexString);

    Queue<Byte> bytes = new LinkedList<Byte>(
        Arrays.asList(
            ArrayUtils.toObject(data)));

    // When
    Message message = Message.decode(bytes);

    // Then
    return;
  }

  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
          + Character.digit(s.charAt(i + 1), 16));
    }
    return data;
  }

}
