package com.discernible.message.header.options;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.discernible.handler.header.options.MobileIdTypeFieldHandler;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;

@RunWith(MockitoJUnitRunner.class)
public class MobileIdTypeFieldTest {

  // Class under test.
  private final MobileIdTypeFieldHandler mobileIdTypeFieldHandler = new MobileIdTypeFieldHandler();

  @Test
  public void test_encode_OFF() {
    test_encode(MobileIdType.OFF, new byte[] {0x01, 0x00});
  }

  @Test
  public void test_decode_OFF() {
    test_decode(MobileIdType.OFF, new byte[] {0x01, 0x00});
  }

  @Test
  public void test_encode_ESN() {
    test_encode(MobileIdType.ESN, new byte[] {0x01, 0x01});
  }

  @Test
  public void test_decode_ESN() {
    test_decode(MobileIdType.ESN, new byte[] {0x01, 0x01});
  }

  @Test
  public void test_encode_IMEI_OR_EID() {
    test_encode(MobileIdType.IMEI_OR_EID, new byte[] {0x01, 0x02});
  }

  @Test
  public void test_decode_IMEI_OR_EID() {
    test_decode(MobileIdType.IMEI_OR_EID, new byte[] {0x01, 0x02});
  }

  @Test
  public void test_encode_IMSI() {
    test_encode(MobileIdType.IMSI, new byte[] {0x01, 0x03});
  }

  @Test
  public void test_decode_IMSI() {
    test_decode(MobileIdType.IMSI, new byte[] {0x01, 0x03});
  }

  @Test
  public void test_encode_USER_DEFINED() {
    test_encode(MobileIdType.USER_DEFINED, new byte[] {0x01, 0x04});
  }

  @Test
  public void test_decode_USER_DEFINED() {
    test_decode(MobileIdType.USER_DEFINED, new byte[] {0x01, 0x04});
  }

  @Test
  public void test_encode_PHONE_NUMBER() {
    test_encode(MobileIdType.PHONE_NUMBER, new byte[] {0x01, 0x05});
  }

  @Test
  public void test_decode_PHONE_NUMBER() {
    test_decode(MobileIdType.PHONE_NUMBER, new byte[] {0x01, 0x05});
  }

  @Test
  public void test_encode_IP() {
    test_encode(MobileIdType.IP, new byte[] {0x01, 0x06});
  }

  @Test
  public void test_decode_IP() {
    test_decode(MobileIdType.IP, new byte[] {0x01, 0x06});
  }

  @Test
  public void test_encode_MEID_IMEI() {
    test_encode(MobileIdType.MEID_IMEI, new byte[] {0x01, 0x07});
  }

  @Test
  public void test_decode_MEID_IMEI() {
    test_decode(MobileIdType.MEID_IMEI, new byte[] {0x01, 0x07});
  }

  private void test_encode(MobileIdTypeField.MobileIdType mobileIdType, byte[] expectedMessageBytes) {

    // Given
    MobileIdTypeField mobileIdField = new MobileIdTypeField(mobileIdType);

    // When
    byte[] actualMessageBytes = mobileIdTypeFieldHandler.encode(mobileIdField);

    // Then
    Assert.assertArrayEquals(String.format("Bytes for %s were not as expected", mobileIdType), expectedMessageBytes, actualMessageBytes);
  }

  private void test_decode(MobileIdTypeField.MobileIdType expectedMobileIdType, byte[] messageBytes) {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList(ArrayUtils.toObject(messageBytes)));

    // When
    MobileIdTypeField mobileIdTypeField = mobileIdTypeFieldHandler.decode(bytes);

    // Then
    Assert.assertEquals(String.format("Did not get expected field: %s", expectedMobileIdType), expectedMobileIdType,
        mobileIdTypeField.getMobileIdType());
  }

}
