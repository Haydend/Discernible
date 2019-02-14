package com.discernible.message.body.type1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.ByteField;
import com.discernible.message.Message;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.MessageBody.MessageType;
import com.discernible.message.body.type1.StatusField.Status;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;

public class AcknowledgeMessageTest {

  @Test
  public void test_encode() {

    // Given
    ByteField mobileId = new ByteField(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 });
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    TypeField typeField = new TypeField(MessageType.MINI_APPLICATION_MESSAGE);
    StatusField statusField = new StatusField(Status.FAILED_AUTHENTICATION_FAILURE);
    AppVersionField appVersionField = new AppVersionField(65, 'b');
    AcknowledgeMessage acknowledgeMessage = new AcknowledgeMessage(typeField, statusField, appVersionField);
    acknowledgeMessage.setSequenceNumber(1);

    Message message = new Message(optonsHeader, acknowledgeMessage);

    // When
    byte[] actualBytes = message.encode();

    // Then
    Assert.assertArrayEquals(
        new byte[] { (byte) 0x83, 0x05, 0x01, 0x02, 0x03, 0x04, 0x05, 0x01, 0x01, 0x02, 0x01, 0x00, 0x01, 0x0C, 0x05, 0x00, 0x06, 0x05, 0x62 },
        actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(
        Arrays.asList((byte) 0x83, (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x01, (byte) 0x01,
            (byte) 0x02, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x0C, (byte) 0x05, (byte) 0x00, (byte) 0x06, (byte) 0x05, (byte) 0x62));

    // When
    Message message = Message.decode(bytes);

    // Then
    ByteField mobileId = new ByteField(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 });
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);
    Assert.assertEquals(optonsHeader, message.getOptionHeader());

    TypeField typeField = new TypeField(MessageType.MINI_APPLICATION_MESSAGE);
    StatusField statusField = new StatusField(Status.FAILED_AUTHENTICATION_FAILURE);
    AppVersionField appVersionField = new AppVersionField(65, 'b');
    AcknowledgeMessage acknowledgeMessage = new AcknowledgeMessage(typeField, statusField, appVersionField);
    acknowledgeMessage.setSequenceNumber(1);
    Assert.assertEquals(acknowledgeMessage, message.getMessageBody());
  }

}
