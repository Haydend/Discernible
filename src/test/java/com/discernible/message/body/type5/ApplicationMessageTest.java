package com.discernible.message.body.type5;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.body.MessageHandler;
import com.discernible.message.body.Message;
import com.discernible.message.body.Message.ServiceType;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.UnitStatusField.Status;
import com.discernible.message.body.type2.CommStatusField;
import com.discernible.message.body.type2.CommStatusField.NetworkTechnology;
import com.discernible.message.body.type2.CoordinateField;
import com.discernible.message.body.type2.FixStatusField;
import com.discernible.message.body.type2.FixStatusField.FixStatus;
import com.discernible.message.body.type2.HdopField;
import com.discernible.message.body.type2.InputField;
import com.discernible.message.body.type5.payload.VehicleIdReport;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;
import com.discernible.util.ByteUtils;

public class ApplicationMessageTest {

  // Class under test.
  private final MessageHandler messageHandler = new MessageHandler();

  @Test
  public void test_encode_vehicleIDReport() {

    // Given
    byte[] mobileId = new byte[] {0x35, (byte) 0x93, 0x16, 0x07, 0x50, (byte) 0x84, 0x37, 0x3f};
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.IMEI_OR_EID);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    LocalDateTime updateTime = LocalDateTime.of(2019, 1, 10, 15, 24, 40);
    LocalDateTime timeOfFix = LocalDateTime.of(2019, 1, 10, 15, 24, 39);
    CoordinateField latitude = new CoordinateField(50.085802799999996);
    CoordinateField longitude = new CoordinateField(8.6736646);
    Integer altitude = 15919;
    Integer speed = 861;
    Short heading = 201;
    Short satellitesCount = 6;
    FixStatusField fixStatus =
        new FixStatusField(EnumSet.of(FixStatus.HISTORIC));
    Integer carrierId = 2;
    Short rssi = -71;
    CommStatusField commStatus = new CommStatusField(true, true, true, true, false, true, NetworkTechnology._2G_NETWORK);
    HdopField hdopField = new HdopField(1.6);
    InputField inputField = new InputField(true, true, false, false, true, false, false, false);
    UnitStatusField unitStatusField = new UnitStatusField(Status.ERROR, Status.ERROR, Status.ERROR, false);

    VehicleIdReport vehicleIdReport =
        new VehicleIdReport("VSSZZZAAZGD309885", 5, "0,1,2,4,7,9,11,14,100,101,102,103,20", "0(0000000000011),1(11100001111)");

    ApplicationMessage applicationMessage =
        new ApplicationMessage(ServiceType.ACKNOWLEDGED_REQUEST, true, updateTime, timeOfFix, latitude, longitude, altitude, speed,
            heading, satellitesCount, fixStatus, carrierId, rssi, commStatus, hdopField, inputField, unitStatusField, vehicleIdReport);
    applicationMessage.setSequenceNumber(7);
    applicationMessage.setOptionHeader(optonsHeader);

    // When
    byte[] messageBytes = messageHandler.encode(applicationMessage, true);

    // Then
    String hexString =
        "8308359316075084373f0102010500075c3763b85c3763b71dda7cac052b7f0600003e2f0000035d00c906200002ffb92f1013000083007250524f544f3a350056494e3a5653535a5a5a41415a474433303938383500504152414d533a302c312c322c342c372c392c31312c31342c3130302c3130312c3130322c3130332c323000494e44435452533a302830303030303030303030303131292c312831313130303030313131312900";
    byte[] expectedBytes = ByteUtils.hexStringToByteArray(hexString);
    Assert.assertArrayEquals(expectedBytes, messageBytes);
  }

  @Test
  public void test_decode_vehicleIDReport() {

    // Given
    String hexString =
        "83000102010220985D0118755D0118751F3F917EFEF9ACCD00003D8C0000006400120D20000AFFB32F073F006C820200000000060002B33D";
    byte[] data = ByteUtils.hexStringToByteArray(hexString);

    Queue<Byte> bytes = new LinkedList<Byte>(
        Arrays.asList(
            ArrayUtils.toObject(data)));

    // When
    Message message = messageHandler.decode(bytes, true);

    return;

    // Then
    // ByteField mobileId = new ByteField(new byte[] {0x35, (byte) 0x93, 0x16, 0x07, 0x50, (byte) 0x84, 0x37, 0x3f});
    // MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.IMEI_OR_EID);
    // OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);
    // Assert.assertEquals(optonsHeader, message.getOptionHeader());
    //
    //
    // ApplicationMessage applicationMessage = (ApplicationMessage) message.getMessageBody();
    //
    // DateTimeField updateTime = new DateTimeField(LocalDateTime.of(2019, 1, 10, 15, 24, 40));
    // Assert.assertEquals(updateTime, applicationMessage.getUpdateTime());
    //
    // DateTimeField timeOfFix = new DateTimeField(LocalDateTime.of(2019, 1, 10, 15, 24, 39));
    // Assert.assertEquals(timeOfFix, applicationMessage.getTimeOfFix());
    //
    // CoordinateField latitude = new CoordinateField(50.085802799999996);
    // Assert.assertEquals(latitude, applicationMessage.getLatitude());
    //
    // CoordinateField longitude = new CoordinateField(8.6736646);
    // Assert.assertEquals(longitude, applicationMessage.getLongitude());
    //
    // SignedIntegerField altitude = new SignedIntegerField(15919);
    // Assert.assertEquals(altitude, applicationMessage.getAltitude());
    //
    // SignedIntegerField speed = new SignedIntegerField(861);
    // Assert.assertEquals(speed, applicationMessage.getSpeed());
    //
    // SignedShortField heading = new SignedShortField((short) 201);
    // Assert.assertEquals(heading, applicationMessage.getHeading());
    //
    // UnsignedShortField satellitesCount = new UnsignedShortField((short) 6);
    // Assert.assertEquals(satellitesCount, applicationMessage.getSatellitesCount());
    //
    // FixStatusField fixStatus =
    // new FixStatusField(EnumSet.of(FixStatus.HISTORIC));
    // Assert.assertEquals(fixStatus, applicationMessage.getFixStatus());
    //
    // UnsignedIntegerField carrierId = new UnsignedIntegerField(2);
    // Assert.assertEquals(carrierId, applicationMessage.getCarrierId());
    //
    // SignedShortField rssi = new SignedShortField((short) -71);
    // Assert.assertEquals(rssi, applicationMessage.getRssi());
    //
    // CommStatusField commStatus = new CommStatusField(true, true, true, true, false, true, NetworkTechnology._2G_NETWORK);
    // Assert.assertEquals(commStatus, applicationMessage.getCommStatus());
    //
    // HdopField hdopField = new HdopField(1.6);
    // Assert.assertEquals(hdopField, applicationMessage.getHdop());
    //
    // InputField inputField = new InputField(true, true, false, false, true, false, false, false);
    // Assert.assertEquals(inputField, applicationMessage.getInput());
    //
    // UnitStatusField unitStatusField = new UnitStatusField(Status.ERROR, Status.ERROR, Status.ERROR, false);
    // Assert.assertEquals(unitStatusField, applicationMessage.getUnitStatus());
    //
    // VehicleIdReport vehicleIdReport =
    // new VehicleIdReport("VSSZZZAAZGD309885", 5, "0,1,2,4,7,9,11,14,100,101,102,103,20", "0(0000000000011),1(11100001111)");
    // Assert.assertEquals(vehicleIdReport, applicationMessage.getApplicationMessagePayload());
    //
    // ApplicationMessage expectedApplicationMessage =
    // new ApplicationMessage(ServiceType.ACKNOWLEDGED_REQUEST, true, updateTime, timeOfFix, latitude, longitude, altitude, speed,
    // heading, satellitesCount, fixStatus, carrierId, rssi, commStatus, hdopField, inputField, unitStatusField, vehicleIdReport);
    // expectedApplicationMessage.setSequenceNumber(7);
    //
    // Assert.assertEquals(expectedApplicationMessage, message.getMessageBody());
  }

}
