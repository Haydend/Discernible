package com.discernible.message.body.type0;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
    ByteField mobileId = new ByteField(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 });
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    NullMessage nullMessage = new NullMessage();
    nullMessage.setSequenceNumber(1);

    Message message = new Message(optonsHeader, nullMessage);

    // When
    byte[] actualBytes = message.encode();

    // Then
    Assert.assertArrayEquals(new byte[] { (byte) 0x83, 0x05, 0x01, 0x02, 0x03, 0x04, 0x05, 0x01, 0x01, 0x00, 0x00, 0x00, 0x01 }, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0x83, (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05,
        (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01));

    // When
    Message message = Message.decode(bytes);

    // Then
    ByteField mobileId = new ByteField(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 });
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);
    Assert.assertEquals(optonsHeader, message.getOptionHeader());

    NullMessage nullMessage = new NullMessage();
    nullMessage.setSequenceNumber(1);
    Assert.assertEquals(nullMessage, message.getMessageBody());
  }

}
