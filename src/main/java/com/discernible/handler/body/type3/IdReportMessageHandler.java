package com.discernible.handler.body.type3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;

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

  public IdReportMessage decodeBody(Queue<Byte> messageBytes, ServiceType serviceType) {

    Short scriptVersion = unsignedShortFieldHandler.decode(messageBytes);
    Short configVersion = unsignedShortFieldHandler.decode(messageBytes);
    AppVersionField firmwareVersion = appVersionFieldHandler.decode(messageBytes);
    Short vehicleClass = unsignedShortFieldHandler.decode(messageBytes);
    UnitStatusField unitStatus = unitStatusFieldHandler.decode(messageBytes);
    Short modemSelection = unsignedShortFieldHandler.decode(messageBytes);
    Short applicationId = unsignedShortFieldHandler.decode(messageBytes);
    Short mobileId = unsignedShortFieldHandler.decode(messageBytes);
    Integer queryId = signedIntegerFieldHandler.decode(messageBytes);
    String esn = packedBcd8ByteFieldHandler.decode(messageBytes);
    String imei = packedBcd8ByteFieldHandler.decode(messageBytes);
    String imsi = packedBcd8ByteFieldHandler.decode(messageBytes);
    String phoneNo = packedBcd8ByteFieldHandler.decode(messageBytes);
    String iccid = packedBcd10ByteFieldHandler.decode(messageBytes);
    Map<String, String> extensionStrings = dataListFieldHandler.decode(messageBytes);

    return new IdReportMessage(serviceType, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus, modemSelection, applicationId,
        mobileId, queryId, esn, imei, imsi, phoneNo, iccid, extensionStrings);
  }

  public byte[] encodeBody(IdReportMessage message) {
    List<Byte> messageBytes = new ArrayList<>();

    add(messageBytes, unsignedShortFieldHandler.encode(message.getScriptVersion()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getConfigVersion()));
    add(messageBytes, appVersionFieldHandler.encode(message.getFirmwareVersion()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getVehicleClass()));
    add(messageBytes, unitStatusFieldHandler.encode(message.getUnitStatus()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getModemSelection()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getApplicationId()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getMobileId()));
    add(messageBytes, signedIntegerFieldHandler.encode(message.getQueryId()));
    add(messageBytes, packedBcd8ByteFieldHandler.encode(message.getEsn()));
    add(messageBytes, packedBcd8ByteFieldHandler.encode(message.getImei()));
    add(messageBytes, packedBcd8ByteFieldHandler.encode(message.getImsi()));
    add(messageBytes, packedBcd8ByteFieldHandler.encode(message.getPhoneNo()));
    add(messageBytes, packedBcd10ByteFieldHandler.encode(message.getIccid()));
    add(messageBytes, dataListFieldHandler.encode(message.getExtensionStrings()));

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private void add(List<Byte> bytes, byte[] toAdd) {
    bytes.addAll(Arrays.asList(ArrayUtils.toObject(toAdd)));
  }

}
