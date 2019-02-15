package com.discernible.message.body.type2;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.ByteField;
import com.discernible.message.DateTimeField;
import com.discernible.message.Message;
import com.discernible.message.SignedIntegerField;
import com.discernible.message.SignedShortField;
import com.discernible.message.UnsignedIntegerField;
import com.discernible.message.UnsignedShortField;
import com.discernible.message.body.MessageBody.ServiceType;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.UnitStatusField.Status;
import com.discernible.message.body.type2.CommStatusField.NetworkTechnology;
import com.discernible.message.body.type2.FixStatusField.FixStatus;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;

public class EventReportMessageTest {

  @Test
  public void test_encode() {

    // Given
    ByteField mobileId = new ByteField(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    DateTimeField updateTime = new DateTimeField(LocalDateTime.of(2012, 5, 17, 02, 38));
    DateTimeField timeOfFix = new DateTimeField(LocalDateTime.of(2012, 5, 17, 02, 38));
    CoordinateField latitude = new CoordinateField(33.1313576);
    CoordinateField longitude = new CoordinateField(-117.2790010);
    SignedIntegerField altitude = new SignedIntegerField(4915);
    SignedIntegerField speed = new SignedIntegerField(0);
    SignedShortField heading = new SignedShortField((short) 4369);
    UnsignedShortField satellitesCount = new UnsignedShortField((short) 2);
    FixStatusField fixStatus =
        new FixStatusField(EnumSet.of(FixStatus.PREDICTED, FixStatus.DIFFERENTIALLY_CORRECTED, FixStatus._2D_FIX, FixStatus.HISTORIC));
    UnsignedIntegerField carrierId = new UnsignedIntegerField(17476);
    SignedShortField rssi = new SignedShortField((short) 21845);
    CommStatusField commStatus = new CommStatusField(false, true, true, false, false, true, NetworkTechnology._3G_NETWORK);
    HdopField hdopField = new HdopField(11.9);
    InputField inputField = new InputField(false, false, false, true, false, false, false, true);
    UnitStatusField unitStatusField = new UnitStatusField(Status.OK, Status.ERROR, Status.ERROR, true);
    UnsignedShortField eventIndex = new UnsignedShortField((short) 16);
    UnsignedShortField eventCode = new UnsignedShortField((short) 17);
    List<AccumulatorField> accumulatorFields = Arrays.asList(new AccumulatorField(new byte[] {0x01, 0x02, 0x03, 0x04}));

    EventReportMessage eventReportMessage = new EventReportMessage(ServiceType.ACKNOWLEDGED_REQUEST, updateTime,
        timeOfFix, latitude, longitude, altitude, speed, heading, satellitesCount, fixStatus, carrierId, rssi, commStatus, hdopField, inputField,
        unitStatusField, eventIndex, eventCode, accumulatorFields);
    eventReportMessage.setSequenceNumber(1);

    Message message = new Message(optonsHeader, eventReportMessage);

    // When
    byte[] messageBytes = message.encode();

    // Then
    byte[] expectedMessageBytes =
        new byte[] {(byte) 0x83, (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x01, (byte) 0x01,
            (byte) 0x01, (byte) 0x02, (byte) 0x00, (byte) 0x01, (byte) 0x4F, (byte) 0xB4, (byte) 0x64, (byte) 0x88, (byte) 0x4F, (byte) 0xB4,
            (byte) 0x64, (byte) 0x88, (byte) 0x13, (byte) 0xBF, (byte) 0x71, (byte) 0xA8, (byte) 0xBA, (byte) 0x18, (byte) 0xA5, (byte) 0x06,
            (byte) 0x00, (byte) 0x00, (byte) 0x13, (byte) 0x33, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0x11,
            (byte) 0x02, (byte) 0x33, (byte) 0x44, (byte) 0x44, (byte) 0x55, (byte) 0x55, (byte) 0x66, (byte) 0x77, (byte) 0x88, (byte) 0x09,
            (byte) 0x10, (byte) 0x11, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04};

    Assert.assertArrayEquals(expectedMessageBytes, messageBytes);
  }

  @Test
  public void test_decode() {

    // Given
    Queue<Byte> bytes = new LinkedList<Byte>(
        Arrays.asList(
            (byte) 0x83, (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x01, (byte) 0x01,
            (byte) 0x01, (byte) 0x02, (byte) 0x00, (byte) 0x01, (byte) 0x4F, (byte) 0xB4, (byte) 0x64, (byte) 0x88, (byte) 0x4F, (byte) 0xB4,
            (byte) 0x64, (byte) 0x88, (byte) 0x13, (byte) 0xBF, (byte) 0x71, (byte) 0xA8, (byte) 0xBA, (byte) 0x18, (byte) 0xA5, (byte) 0x06,
            (byte) 0x00, (byte) 0x00, (byte) 0x13, (byte) 0x33, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0x11,
            (byte) 0x02, (byte) 0x33, (byte) 0x44, (byte) 0x44, (byte) 0x55, (byte) 0x55, (byte) 0x66, (byte) 0x77, (byte) 0x88, (byte) 0x09,
            (byte) 0x10, (byte) 0x11, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04));

    // When
    Message message = Message.decode(bytes);

    // Then
    ByteField mobileId = new ByteField(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.ESN);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);
    Assert.assertEquals(optonsHeader, message.getOptionHeader());

    DateTimeField updateTime = new DateTimeField(LocalDateTime.of(2012, 5, 17, 02, 38));
    DateTimeField timeOfFix = new DateTimeField(LocalDateTime.of(2012, 5, 17, 02, 38));
    CoordinateField latitude = new CoordinateField(33.1313576);
    CoordinateField longitude = new CoordinateField(-117.2790010);
    SignedIntegerField altitude = new SignedIntegerField(4915);
    SignedIntegerField speed = new SignedIntegerField(0);
    SignedShortField heading = new SignedShortField((short) 4369);
    UnsignedShortField satellitesCount = new UnsignedShortField((short) 2);
    FixStatusField fixStatus =
        new FixStatusField(EnumSet.of(FixStatus.PREDICTED, FixStatus.DIFFERENTIALLY_CORRECTED, FixStatus._2D_FIX, FixStatus.HISTORIC));
    UnsignedIntegerField carrierId = new UnsignedIntegerField(17476);
    SignedShortField rssi = new SignedShortField((short) 21845);
    CommStatusField commStatus = new CommStatusField(false, true, true, false, false, true, NetworkTechnology._3G_NETWORK);
    HdopField hdopField = new HdopField(11.9);
    InputField inputField = new InputField(false, false, false, true, false, false, false, true);
    UnitStatusField unitStatusField = new UnitStatusField(Status.OK, Status.ERROR, Status.ERROR, true);
    UnsignedShortField eventIndex = new UnsignedShortField((short) 16);
    UnsignedShortField eventCode = new UnsignedShortField((short) 17);
    List<AccumulatorField> accumulatorFields = Arrays.asList(new AccumulatorField(new byte[] {0x01, 0x02, 0x03, 0x04}));

    EventReportMessage eventReportMessage = new EventReportMessage(ServiceType.ACKNOWLEDGED_REQUEST, updateTime,
        timeOfFix, latitude, longitude, altitude, speed, heading, satellitesCount, fixStatus, carrierId, rssi, commStatus, hdopField, inputField,
        unitStatusField, eventIndex, eventCode, accumulatorFields);

    Assert.assertEquals(eventReportMessage, message.getMessageBody());
  }

}
