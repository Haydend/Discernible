package com.discernible.message.header.options;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.header.options.OptionsHeaderFieldHandler;
import com.discernible.message.Socket;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.extension.OptionExtension;

public class OptionHeaderTest {

  private OptionsHeaderFieldHandler optionsHeaderFieldHandler = new OptionsHeaderFieldHandler();

  @Test
  public void test_encode_mobileId() {

    // Given
    byte[] mobileId = new byte[] {0x01, 0x02, 0x03, 0x04};
    OptionsHeader optionsHeader = new OptionsHeader(mobileId, null, null, null, null, null, null);

    // When
    byte[] actualBytes = optionsHeaderFieldHandler.encode(optionsHeader);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b10000001, 0x04, 0x01, 0x02, 0x03, 0x04}, actualBytes);
  }

  @Test
  public void test_decode_mobileId() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b10000001, (byte) 0x04, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04));

    // When
    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(bytes);

    // Then
    Assert.assertNotNull(optionsHeader.getMobileId());
    Assert.assertNull(optionsHeader.getMobileIdType());
    Assert.assertNull(optionsHeader.getAuthentication());
    Assert.assertNull(optionsHeader.getRouting());
    Assert.assertNull(optionsHeader.getForwarding());
    Assert.assertNull(optionsHeader.getResponseRedirection());
    Assert.assertNull(optionsHeader.getOptionExtension());

    Assert.assertArrayEquals(new byte[] {0x01, 0x02, 0x03, 0x04}, optionsHeader.getMobileId());
  }

  @Test
  public void test_encode_mobileIdType() {

    // Given
    MobileIdTypeField mobileIdTypeField = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optionsHeader = new OptionsHeader(null, mobileIdTypeField, null, null, null, null, null);

    // When
    byte[] actualBytes = optionsHeaderFieldHandler.encode(optionsHeader);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b10000010, 0x01, 0x01}, actualBytes);
  }

  @Test
  public void test_decode_mobileIdType() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b10000010, (byte) 0x01, (byte) 0x01));

    // When
    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionsHeader.getMobileId());
    Assert.assertNotNull(optionsHeader.getMobileIdType());
    Assert.assertNull(optionsHeader.getAuthentication());
    Assert.assertNull(optionsHeader.getRouting());
    Assert.assertNull(optionsHeader.getForwarding());
    Assert.assertNull(optionsHeader.getResponseRedirection());
    Assert.assertNull(optionsHeader.getOptionExtension());

    Assert.assertEquals(MobileIdType.ESN, optionsHeader.getMobileIdType().getMobileIdType());
  }

  @Test
  public void test_encode_authentication() {

    // Given
    byte[] authentication = new byte[] {0x01, 0x02, 0x03, 0x04};
    OptionsHeader optionsHeader = new OptionsHeader(null, null, authentication, null, null, null, null);

    // When
    byte[] actualBytes = optionsHeaderFieldHandler.encode(optionsHeader);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b10000100, 0x04, 0x01, 0x02, 0x03, 0x04}, actualBytes);
  }

  @Test
  public void test_decode_authentication() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b10000100, (byte) 0x04, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04));

    // When
    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionsHeader.getMobileId());
    Assert.assertNull(optionsHeader.getMobileIdType());
    Assert.assertNotNull(optionsHeader.getAuthentication());
    Assert.assertNull(optionsHeader.getRouting());
    Assert.assertNull(optionsHeader.getForwarding());
    Assert.assertNull(optionsHeader.getResponseRedirection());
    Assert.assertNull(optionsHeader.getOptionExtension());

    Assert.assertArrayEquals(new byte[] {0x01, 0x02, 0x03, 0x04}, optionsHeader.getAuthentication());
  }

  @Test
  public void test_encode_routing() {

    // Given
    byte[] routing = new byte[] {0x01, 0x02, 0x03, 0x04};
    OptionsHeader optionsHeader = new OptionsHeader(null, null, null, routing, null, null, null);

    // When
    byte[] actualBytes = optionsHeaderFieldHandler.encode(optionsHeader);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b10001000, 0x04, 0x01, 0x02, 0x03, 0x04}, actualBytes);
  }

  @Test
  public void test_decode_routing() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b10001000, (byte) 0x04, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04));

    // When
    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionsHeader.getMobileId());
    Assert.assertNull(optionsHeader.getMobileIdType());
    Assert.assertNull(optionsHeader.getAuthentication());
    Assert.assertNotNull(optionsHeader.getRouting());
    Assert.assertNull(optionsHeader.getForwarding());
    Assert.assertNull(optionsHeader.getResponseRedirection());
    Assert.assertNull(optionsHeader.getOptionExtension());

    Assert.assertArrayEquals(new byte[] {0x01, 0x02, 0x03, 0x04}, optionsHeader.getRouting());
  }

  @Test
  public void test_encode_forwarding() {

    // Given
    ForwardingField forwardingField = new ForwardingField("192.168.0.1", 5000, Protocol.TCP, ForwardingOperationType.FORWARD);
    OptionsHeader optionsHeader = new OptionsHeader(null, null, null, null, forwardingField, null, null);

    // When
    byte[] actualBytes = optionsHeaderFieldHandler.encode(optionsHeader);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b10010000, 0x08, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88, 0x06, 0x00},
        actualBytes);
  }

  @Test
  public void test_decode_forwarding() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b10010000, (byte) 0x08, (byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01,
        (byte) 0x13, (byte) 0x88, (byte) 0x06, (byte) 0x00));

    // When
    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionsHeader.getMobileId());
    Assert.assertNull(optionsHeader.getMobileIdType());
    Assert.assertNull(optionsHeader.getAuthentication());
    Assert.assertNull(optionsHeader.getRouting());
    Assert.assertNotNull(optionsHeader.getForwarding());
    Assert.assertNull(optionsHeader.getResponseRedirection());
    Assert.assertNull(optionsHeader.getOptionExtension());

    Assert.assertEquals("192.168.0.1", optionsHeader.getForwarding().getIp().getIP());
    Assert.assertEquals(5000, (int) optionsHeader.getForwarding().getPort());
    Assert.assertEquals(Protocol.TCP, optionsHeader.getForwarding().getForwardingProtocol());
    Assert.assertEquals(ForwardingOperationType.FORWARD, optionsHeader.getForwarding().getForwardingOperationType());
  }

  @Test
  public void test_encode_socketField() {

    // Given
    Socket responseRedirection = new Socket("192.168.0.1", 5000);
    OptionsHeader optionsHeader = new OptionsHeader(null, null, null, null, null, responseRedirection, null);

    // When
    byte[] actualBytes = optionsHeaderFieldHandler.encode(optionsHeader);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b10100000, 0x06, (byte) 0xC0, (byte) 0xA8, 0x00, 0x01, 0x13, (byte) 0x88}, actualBytes);
  }

  @Test
  public void test_decode_socketField() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(
        Arrays.asList((byte) 0b10100000, (byte) 0x06, (byte) 0xC0, (byte) 0xA8, (byte) 0x00, (byte) 0x01, (byte) 0x13, (byte) 0x88));

    // When
    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionsHeader.getMobileId());
    Assert.assertNull(optionsHeader.getMobileIdType());
    Assert.assertNull(optionsHeader.getAuthentication());
    Assert.assertNull(optionsHeader.getRouting());
    Assert.assertNull(optionsHeader.getForwarding());
    Assert.assertNotNull(optionsHeader.getResponseRedirection());
    Assert.assertNull(optionsHeader.getOptionExtension());

    Assert.assertEquals("192.168.0.1", optionsHeader.getResponseRedirection().getIpText());
    Assert.assertEquals(5000, optionsHeader.getResponseRedirection().getPort());
  }

  @Test
  public void test_encode_optionExtension() {

    // Given
    OptionExtension optionExtension = new OptionExtension(null, null, null, false, null);
    OptionsHeader optionsHeader = new OptionsHeader(null, null, null, null, null, null, optionExtension);

    // When
    byte[] actualBytes = optionsHeaderFieldHandler.encode(optionsHeader);

    // Then
    Assert.assertArrayEquals(new byte[] {(byte) 0b11000000, 0x01, (byte) 0b00000000}, actualBytes);
  }

  @Test
  public void test_decode_optionExtension() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(Arrays.asList((byte) 0b11000000, (byte) 0x01, (byte) 0b00000000));

    // When
    OptionsHeader optionsHeader = optionsHeaderFieldHandler.decode(bytes);

    // Then
    Assert.assertNull(optionsHeader.getMobileId());
    Assert.assertNull(optionsHeader.getMobileIdType());
    Assert.assertNull(optionsHeader.getAuthentication());
    Assert.assertNull(optionsHeader.getRouting());
    Assert.assertNull(optionsHeader.getForwarding());
    Assert.assertNull(optionsHeader.getResponseRedirection());
    Assert.assertNotNull(optionsHeader.getOptionExtension());
  }
}
