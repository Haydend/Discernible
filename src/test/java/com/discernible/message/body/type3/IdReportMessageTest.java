package com.discernible.message.body.type3;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.body.MessageHandler;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.Message;
import com.discernible.message.body.Message.ServiceType;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.UnitStatusField.Status;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.MobileIdTypeField.MobileIdType;
import com.discernible.message.header.options.OptionsHeader;
import com.discernible.util.ByteUtils;

public class IdReportMessageTest {

  // Class under test.
  private final MessageHandler messageHandler = new MessageHandler();

  @Test
  public void test_encode() {

    // Given
    byte[] mobileId = new byte[] {0x35, (byte) 0x93, 0x16, 0x07, 0x51, 0x77, 0x50, (byte) 0x8F};
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.IMEI_OR_EID);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    Short scriptVersion = 200;
    Short configVersion = 32;
    AppVersionField firmwareVersion = new AppVersionField(39, 'l');
    Short vehicleClass = 0;
    UnitStatusField unitStatus = new UnitStatusField(Status.ERROR, Status.ERROR, Status.ERROR, false);
    Short modemSelection = 35;
    Short applicationId = 195;
    Short mobileIdBody = 2;
    Integer queryId = 0;
    String esn = "4532459871";
    String imei = "359316075177508";
    String imsi = "204080812249437";
    String phoneNo = "";
    String iccid = "8931088817125056770";

    Map<String, String> extensionStrings = new HashMap<>();
    extensionStrings.put("OTA", "1|0;0,1,9,11|4;0,4|7;0");
    extensionStrings.put("OTASTAT", "1548950433,0,1,5,0,\"\"");
    extensionStrings.put("FTBL", "4,0,226B|0,1,7C3B");

    IdReportMessage idReportMessage =
        new IdReportMessage(ServiceType.RESPONSE_TO_AN_ACKNOWLEDGED_REQUEST, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus,
            modemSelection, applicationId, mobileIdBody, queryId, esn, imei, imsi, phoneNo, iccid, extensionStrings);
    idReportMessage.setOptionHeader(optonsHeader);
    idReportMessage.setSequenceNumber(0);

    // When
    byte[] messageBytes = messageHandler.encode(idReportMessage, true);

    // Then
    String hexString =
        "8308359316075177508f010202030000c82033396c000023c302000000004532459871ffffff359316075177508f204080812249437fffffffffffffffff8931088817125056770f4654424c3a342c302c323236427c302c312c37433342004f54413a317c303b302c312c392c31317c343b302c347c373b30004f5441535441543a313534383935303433332c302c312c352c302c222200";
    byte[] expectedBytes = ByteUtils.hexStringToByteArray(hexString);
    Assert.assertArrayEquals(expectedBytes, messageBytes);
  }

  @Test
  public void test_decode() {

    // Given
    String hexString =
        "8308359316075177508f010202030000c82033396c000023c302000000004532459871ffffff359316075177508f204080812249437fffffffffffffffff8931088817125056770f4654424c3a342c302c323236427c302c312c37433342004f54413a317c303b302c312c392c31317c343b302c347c373b30004f5441535441543a313534383935303433332c302c312c352c302c222200";
    byte[] data = ByteUtils.hexStringToByteArray(hexString);
    ByteInputStream in = new ByteInputStream(data);

    // When
    Message message = messageHandler.decode(in, true);

    // Then
    byte[] mobileId = new byte[] {0x35, (byte) 0x93, 0x16, 0x07, 0x51, 0x77, 0x50, (byte) 0x8F};
    MobileIdTypeField mobileIdType = new MobileIdTypeField(MobileIdType.IMEI_OR_EID);
    OptionsHeader optonsHeader = new OptionsHeader(mobileId, mobileIdType, null, null, null, null, null);

    Short scriptVersion = 200;
    Short configVersion = 32;
    AppVersionField firmwareVersion = new AppVersionField(39, 'l');
    Short vehicleClass = 0;
    UnitStatusField unitStatus = new UnitStatusField(Status.ERROR, Status.ERROR, Status.ERROR, false);
    Short modemSelection = 35;
    Short applicationId = 195;
    Short mobileIdBody = 2;
    Integer queryId = 0;
    String esn = "4532459871";
    String imei = "359316075177508";
    String imsi = "204080812249437";
    String phoneNo = "";
    String iccid = "8931088817125056770";

    Map<String, String> extensionStrings = new HashMap<>();
    extensionStrings.put("OTA", "1|0;0,1,9,11|4;0,4|7;0");
    extensionStrings.put("OTASTAT", "1548950433,0,1,5,0,\"\"");
    extensionStrings.put("FTBL", "4,0,226B|0,1,7C3B");

    IdReportMessage idReportMessage =
        new IdReportMessage(ServiceType.RESPONSE_TO_AN_ACKNOWLEDGED_REQUEST, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus,
            modemSelection, applicationId, mobileIdBody, queryId, esn, imei, imsi, phoneNo, iccid, extensionStrings);
    idReportMessage.setOptionHeader(optonsHeader);
    idReportMessage.setSequenceNumber(0);

    Assert.assertEquals(idReportMessage, message);
  }

}
