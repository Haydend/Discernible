package com.discernible.message.body.type0;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.MessageHandler;
import com.discernible.message.body.Message;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class NullMessageTest {

  // Class under test.
  private final MessageHandler messageHandler = new MessageHandler();

  @Test
  public void test_encode() {

    // Given
    byte[] mobileId = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05};
    MobileIdType mobileIdType = MobileIdType.ESN;
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    NullMessage message = new NullMessage();
    message.setSequenceNumber(1);
    message.setOptionHeader(optonsHeader);

    // When
    byte[] actualBytes = messageHandler.encode(message, true);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0x83, 0x05, 0x01, 0x02, 0x03, 0x04, 0x05, 0x01, 0x01, 0x00, 0x00, 0x00, 0x01}, actualBytes);
  }

  @Test
  public void test_decode() {

    // Given
    JBBPBitInputStream bytes = new JBBPBitInputStream(
        new ByteArrayInputStream(
            new byte[] {(byte) 0x83, (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05,
                (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01}));

    // When
    Message message = messageHandler.decode(bytes, true);

    // Then
    byte[] mobileId = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05};
    MobileIdType mobileIdType = MobileIdType.ESN;
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);
    Assert.assertEquals(optonsHeader, message.getOptionHeader());

    NullMessage nullMessage = new NullMessage();
    nullMessage.setSequenceNumber(1);
    nullMessage.setOptionHeader(optonsHeader);
    Assert.assertEquals(nullMessage, message);
  }

}
