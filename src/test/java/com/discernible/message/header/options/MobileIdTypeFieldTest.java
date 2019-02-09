package com.discernible.message.header.options;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;

@RunWith(MockitoJUnitRunner.class)
public class MobileIdTypeFieldTest {

	@Test
	public void test_encode_OFF() {
		test_encode(MobileIdType.OFF, new byte[] { 0x00 });
	}

	@Test
	public void test_encode_ESN() {
		test_encode(MobileIdType.ESN, new byte[] { 0x01 });
	}

	@Test
	public void test_encode_IMEI_OR_EID() {
		test_encode(MobileIdType.IMEI_OR_EID, new byte[] { 0x02 });
	}

	@Test
	public void test_encode_IMSI() {
		test_encode(MobileIdType.IMSI, new byte[] { 0x03 });
	}

	@Test
	public void test_encode_USER_DEFINED() {
		test_encode(MobileIdType.USER_DEFINED, new byte[] { 0x04 });
	}

	@Test
	public void test_encode_PHONE_NUMBER() {
		test_encode(MobileIdType.PHONE_NUMBER, new byte[] { 0x05 });
	}

	@Test
	public void test_encode_IP() {
		test_encode(MobileIdType.IP, new byte[] { 0x06 });
	}

	@Test
	public void test_encode_MEID_IMEI() {
		test_encode(MobileIdType.MEID_IMEI, new byte[] { 0x07 });
	}

	private void test_encode(MobileIdTypeField.MobileIdType mobileIdType, byte[] expectedMessageBytes) {

		// Given
		MobileIdTypeField mobileIdField = new MobileIdTypeField(mobileIdType);

		// When
		byte[] actualMessageBytes = mobileIdField.encode();

		// Then
		Assert.assertArrayEquals(String.format("Bytes for %s were not as expected", mobileIdType), expectedMessageBytes,
				actualMessageBytes);
	}

}
