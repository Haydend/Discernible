package com.discernible.message.body.type1;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.MessageHandler;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.Message;
import com.discernible.message.body.Message.MessageType;
import com.discernible.message.body.type1.StatusField.Status;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class AcknowledgeMessageTest {

  // Class under test.
  private MessageHandler messageHandler = new MessageHandler();

  @Test
  public void test_encode() {

    // Given
    byte[] mobileId = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05};
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    TypeField typeField = new TypeField(MessageType.MINI_APPLICATION_MESSAGE);
    StatusField statusField = new StatusField(Status.FAILED_AUTHENTICATION_FAILURE);
    AppVersionField appVersionField = new AppVersionField(65, 'b');

    AcknowledgeMessage acknowledgeMessage = new AcknowledgeMessage(typeField, statusField, appVersionField);
    acknowledgeMessage.setSequenceNumber(1);
    acknowledgeMessage.setOptionHeader(optonsHeader);

    // When
    byte[] actualBytes = messageHandler.encode(acknowledgeMessage, false);

    // Then
    Assert.assertArrayEquals(
        new byte[] {(byte) 0x83, 0x05, 0x01, 0x02, 0x03, 0x04, 0x05, 0x01, 0x01, 0x02, 0x01, 0x00, 0x01, 0x0C, 0x05, 0x00, 0x36, 0x35, 0x62},
        actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x83, (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x01, (byte) 0x01,
                (byte) 0x02, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x0C, (byte) 0x05, (byte) 0x00, (byte) 0x36, (byte) 0x35, (byte) 0x62}));

    // When
    Message message = messageHandler.decode(bytes, false);

    // Then
    byte[] mobileId = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05};
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);
    Assert.assertEquals(optonsHeader, message.getOptionHeader());

    TypeField typeField = new TypeField(MessageType.MINI_APPLICATION_MESSAGE);
    StatusField statusField = new StatusField(Status.FAILED_AUTHENTICATION_FAILURE);
    AppVersionField appVersionField = new AppVersionField(65, 'b');

    AcknowledgeMessage acknowledgeMessage = new AcknowledgeMessage(typeField, statusField, appVersionField);
    acknowledgeMessage.setSequenceNumber(1);
    acknowledgeMessage.setOptionHeader(optonsHeader);

    Assert.assertEquals(acknowledgeMessage, message);
  }

}
