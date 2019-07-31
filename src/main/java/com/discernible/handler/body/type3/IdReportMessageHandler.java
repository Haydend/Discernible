package com.discernible.handler.body.type3;

import java.util.Map;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.DataListFieldHandler;
import com.discernible.handler.SignedIntegerFieldHandler;
import com.discernible.handler.UnsignedShortFieldHandler;
import com.discernible.handler.body.AppVersionFieldHandler;
import com.discernible.handler.body.PackedBcd10ByteFieldHandler;
import com.discernible.handler.body.PackedBcd8ByteFieldHandler;
import com.discernible.handler.body.UnitStatusFieldHandler;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.Message.ServiceType;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.type3.IdReportMessage;

public class IdReportMessageHandler {

  private final UnsignedShortFieldHandler unsignedShortFieldHandler = new UnsignedShortFieldHandler();
  private final AppVersionFieldHandler appVersionFieldHandler = new AppVersionFieldHandler();
  private final UnitStatusFieldHandler unitStatusFieldHandler = new UnitStatusFieldHandler();
  private final SignedIntegerFieldHandler signedIntegerFieldHandler = new SignedIntegerFieldHandler();
  private final PackedBcd8ByteFieldHandler packedBcd8ByteFieldHandler = new PackedBcd8ByteFieldHandler();
  private final PackedBcd10ByteFieldHandler packedBcd10ByteFieldHandler = new PackedBcd10ByteFieldHandler();
  private final DataListFieldHandler dataListFieldHandler = new DataListFieldHandler();

  public IdReportMessage decodeBody(ByteInputStream in, ServiceType serviceType) {

    Short scriptVersion = unsignedShortFieldHandler.decode(in);
    Short configVersion = unsignedShortFieldHandler.decode(in);
    AppVersionField firmwareVersion = appVersionFieldHandler.decode(in);
    Short vehicleClass = unsignedShortFieldHandler.decode(in);
    UnitStatusField unitStatus = unitStatusFieldHandler.decode(in);
    Short modemSelection = unsignedShortFieldHandler.decode(in);
    Short applicationId = unsignedShortFieldHandler.decode(in);
    Short mobileId = unsignedShortFieldHandler.decode(in);
    Integer queryId = signedIntegerFieldHandler.decode(in);
    String esn = packedBcd8ByteFieldHandler.decode(in);
    String imei = packedBcd8ByteFieldHandler.decode(in);
    String imsi = packedBcd8ByteFieldHandler.decode(in);
    String phoneNo = packedBcd8ByteFieldHandler.decode(in);
    String iccid = packedBcd10ByteFieldHandler.decode(in);
    Map<String, String> extensionStrings = dataListFieldHandler.decode(in);

    return new IdReportMessage(serviceType, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus, modemSelection, applicationId,
        mobileId, queryId, esn, imei, imsi, phoneNo, iccid, extensionStrings);
  }

  public void encodeBody(IdReportMessage message, ByteOutputStream output) {
    unsignedShortFieldHandler.encode(message.getScriptVersion(), output);
    unsignedShortFieldHandler.encode(message.getConfigVersion(), output);
    appVersionFieldHandler.encode(message.getFirmwareVersion(), output);
    unsignedShortFieldHandler.encode(message.getVehicleClass(), output);
    unitStatusFieldHandler.encode(message.getUnitStatus(), output);
    unsignedShortFieldHandler.encode(message.getModemSelection(), output);
    unsignedShortFieldHandler.encode(message.getApplicationId(), output);
    unsignedShortFieldHandler.encode(message.getMobileId(), output);
    signedIntegerFieldHandler.encode(message.getQueryId(), output);
    packedBcd8ByteFieldHandler.encode(message.getEsn(), output);
    packedBcd8ByteFieldHandler.encode(message.getImei(), output);
    packedBcd8ByteFieldHandler.encode(message.getImsi(), output);
    packedBcd8ByteFieldHandler.encode(message.getPhoneNo(), output);
    packedBcd10ByteFieldHandler.encode(message.getIccid(), output);
    dataListFieldHandler.encode(message.getExtensionStrings(), output);
  }

}
