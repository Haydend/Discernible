package com.discernible.message.body.type3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import com.discernible.message.ByteField;
import com.discernible.message.ExtensionStringsField;
import com.discernible.message.Message;
import com.discernible.message.SignedIntegerField;
import com.discernible.message.UnsignedShortField;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.MessageBody.ServiceType;
import com.discernible.message.body.PackedBcd10ByteField;
import com.discernible.message.body.PackedBcd8ByteField;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.UnitStatusField.Status;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;

public class IdReportMessageTest {

  @Test
  public void test_encode() {

    // Given
    ByteField mobileId = new ByteField(new byte[] {0x35, (byte) 0x93, 0x16, 0x07, 0x51, 0x77, 0x50, (byte) 0x8F});
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.IMEI_OR_EID);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    UnsignedShortField scriptVersion = new UnsignedShortField((short) 200);
    UnsignedShortField configVersion = new UnsignedShortField((short) 32);
    AppVersionField firmwareVersion = new AppVersionField(39, 'l');
    UnsignedShortField vehicleClass = new UnsignedShortField((short) 0);
    UnitStatusField unitStatus = new UnitStatusField(Status.ERROR, Status.ERROR, Status.ERROR, false);
    UnsignedShortField modemSelection = new UnsignedShortField((short) 35);
    UnsignedShortField applicationId = new UnsignedShortField((short) 195);
    UnsignedShortField mobileIdBody = new UnsignedShortField((short) 2);
    SignedIntegerField queryId = new SignedIntegerField(0);
    PackedBcd8ByteField esn = new PackedBcd8ByteField("4532459871");
    PackedBcd8ByteField imei = new PackedBcd8ByteField("359316075177508");
    PackedBcd8ByteField imsi = new PackedBcd8ByteField("204080812249437");
    PackedBcd8ByteField phoneNo = new PackedBcd8ByteField("");
    PackedBcd10ByteField iccid = new PackedBcd10ByteField("8931088817125056770");
    ExtensionStringsField extensionStrings =
        new ExtensionStringsField("OTA:1|0;0,1,9,11|4;0,4|7;0\0OTASTAT:1548950433,0,1,5,0,\"\"\0FTBL:4,0,226B|0,1,7C3B\0");

    IdReportMessage idReportMessage =
        new IdReportMessage(ServiceType.RESPONSE_TO_AN_ACKNOWLEDGED_REQUEST, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus,
            modemSelection, applicationId, mobileIdBody, queryId, esn, imei, imsi, phoneNo, iccid, extensionStrings);

    Message message = new Message(optonsHeader, idReportMessage);

    // When
    byte[] messageBytes = message.encode();

    // Then
    String hexString =
        "8308359316075177508f010202030000c82033396c000023c302000000004532459871ffffff359316075177508f204080812249437fffffffffffffffff8931088817125056770f4f54413a317c303b302c312c392c31317c343b302c347c373b30004f5441535441543a313534383935303433332c302c312c352c302c2222004654424c3a342c302c323236427c302c312c3743334200";
    byte[] expectedBytes = hexStringToByteArray(hexString);
    Assert.assertArrayEquals(expectedBytes, messageBytes);
  }

  @Test
  public void test_decode() {

    // Given
    String hexString =
        "8308359316075177508f010202030000c82033396c000023c302000000004532459871ffffff359316075177508f204080812249437fffffffffffffffff8931088817125056770f4f54413a317c303b302c312c392c31317c343b302c347c373b30004f5441535441543a313534383935303433332c302c312c352c302c2222004654424c3a342c302c323236427c302c312c3743334200";
    byte[] data = hexStringToByteArray(hexString);

    Queue<Byte> bytes = new LinkedList<Byte>(
        Arrays.asList(
            ArrayUtils.toObject(data)));

    // When
    Message message = Message.decode(bytes);

    // Then
    ByteField mobileId = new ByteField(new byte[] {0x35, (byte) 0x93, 0x16, 0x07, 0x51, 0x77, 0x50, (byte) 0x8F});
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.IMEI_OR_EID);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);
    Assert.assertEquals(optonsHeader, message.getOptionHeader());

    UnsignedShortField scriptVersion = new UnsignedShortField((short) 200);
    UnsignedShortField configVersion = new UnsignedShortField((short) 32);
    AppVersionField firmwareVersion = new AppVersionField(39, 'l');
    UnsignedShortField vehicleClass = new UnsignedShortField((short) 0);
    UnitStatusField unitStatus = new UnitStatusField(Status.ERROR, Status.ERROR, Status.ERROR, false);
    UnsignedShortField modemSelection = new UnsignedShortField((short) 35);
    UnsignedShortField applicationId = new UnsignedShortField((short) 195);
    UnsignedShortField mobileIdBody = new UnsignedShortField((short) 2);
    SignedIntegerField queryId = new SignedIntegerField(0);
    PackedBcd8ByteField esn = new PackedBcd8ByteField("4532459871");
    PackedBcd8ByteField imei = new PackedBcd8ByteField("359316075177508");
    PackedBcd8ByteField imsi = new PackedBcd8ByteField("204080812249437");
    PackedBcd8ByteField phoneNo = new PackedBcd8ByteField("");
    PackedBcd10ByteField iccid = new PackedBcd10ByteField("8931088817125056770");
    ExtensionStringsField extensionStrings =
        new ExtensionStringsField("OTA:1|0;0,1,9,11|4;0,4|7;0\0OTASTAT:1548950433,0,1,5,0,\"\"\0FTBL:4,0,226B|0,1,7C3B\0");

    IdReportMessage idReportMessage =
        new IdReportMessage(ServiceType.RESPONSE_TO_AN_ACKNOWLEDGED_REQUEST, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus,
            modemSelection, applicationId, mobileIdBody, queryId, esn, imei, imsi, phoneNo, iccid, extensionStrings);

    Assert.assertEquals(idReportMessage, message.getMessageBody());
  }

  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
          + Character.digit(s.charAt(i + 1), 16));
    }
    return data;
  }

}
