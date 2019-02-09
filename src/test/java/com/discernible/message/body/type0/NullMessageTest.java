package com.discernible.message.body.type0;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.ByteField;
import com.discernible.message.Message;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;

public class NullMessageTest {

  @Test
  public void test_encode() {

    // Given
    byte[] mobileId = new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 };

    OptionsHeader optonsHeader = new OptionsHeader();
    optonsHeader.setMobileId(new ByteField(mobileId).withLength());
    optonsHeader.setMobileIdType(new MobileIdTypeField(MobileIdType.ESN).withLength());

    NullMessage nullMessage = new NullMessage();
    nullMessage.setSequenceNumber(1);

    Message message = new Message(optonsHeader, nullMessage);

    // When
    byte[] actualBytes = message.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { (byte) 0x83, 0x05, 0x01, 0x02, 0x03, 0x04, 0x05, 0x01, 0x01, 0x00, 0x00, 0x00, 0x01 }, actualBytes);
  }

}
