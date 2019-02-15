package com.discernible.message.body.type3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.message.DataListField;
import com.discernible.message.SignedIntegerField;
import com.discernible.message.UnsignedShortField;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.MessageBody;
import com.discernible.message.body.PackedBcd10ByteField;
import com.discernible.message.body.PackedBcd8ByteField;
import com.discernible.message.body.UnitStatusField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IdReportMessage extends MessageBody {

  private ServiceType serviceType;
  private UnsignedShortField scriptVersion;
  private UnsignedShortField configVersion;
  private AppVersionField firmwareVersion;
  private UnsignedShortField vehicleClass;
  private UnitStatusField unitStatus;
  private UnsignedShortField modemSelection;
  private UnsignedShortField applicationId;
  private UnsignedShortField mobileId;
  private SignedIntegerField queryId;
  private PackedBcd8ByteField esn;
  private PackedBcd8ByteField imei;
  private PackedBcd8ByteField imsi;
  private PackedBcd8ByteField phoneNo;
  private PackedBcd10ByteField iccid;
  private DataListField extensionStrings;

  public static IdReportMessage decodeBody(Queue<Byte> messageBytes, ServiceType serviceType) {

    UnsignedShortField scriptVersion = UnsignedShortField.decode(messageBytes);
    UnsignedShortField configVersion = UnsignedShortField.decode(messageBytes);
    AppVersionField firmwareVersion = AppVersionField.decode(messageBytes);
    UnsignedShortField vehicleClass = UnsignedShortField.decode(messageBytes);
    UnitStatusField unitStatus = UnitStatusField.decode(messageBytes);
    UnsignedShortField modemSelection = UnsignedShortField.decode(messageBytes);
    UnsignedShortField applicationId = UnsignedShortField.decode(messageBytes);
    UnsignedShortField mobileId = UnsignedShortField.decode(messageBytes);
    SignedIntegerField queryId = SignedIntegerField.decode(messageBytes);
    PackedBcd8ByteField esn = PackedBcd8ByteField.decode(messageBytes);
    PackedBcd8ByteField imei = PackedBcd8ByteField.decode(messageBytes);
    PackedBcd8ByteField imsi = PackedBcd8ByteField.decode(messageBytes);
    PackedBcd8ByteField phoneNo = PackedBcd8ByteField.decode(messageBytes);
    PackedBcd10ByteField iccid = PackedBcd10ByteField.decode(messageBytes);
    DataListField extensionStrings = DataListField.decode(messageBytes);

    return new IdReportMessage(serviceType, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus, modemSelection, applicationId,
        mobileId, queryId, esn, imei, imsi, phoneNo, iccid, extensionStrings);
  }

  @Override
  public ServiceType getServiceType() {
    return serviceType;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.ID_REPORT_MESSAGE;
  }

  @Override
  public byte[] encodeBody() {
    List<Byte> messageBytes = new ArrayList<>();

    add(messageBytes, scriptVersion.encode());
    add(messageBytes, configVersion.encode());
    add(messageBytes, firmwareVersion.encode());
    add(messageBytes, vehicleClass.encode());
    add(messageBytes, unitStatus.encode());
    add(messageBytes, modemSelection.encode());
    add(messageBytes, applicationId.encode());
    add(messageBytes, mobileId.encode());
    add(messageBytes, queryId.encode());
    add(messageBytes, esn.encode());
    add(messageBytes, imei.encode());
    add(messageBytes, imsi.encode());
    add(messageBytes, phoneNo.encode());
    add(messageBytes, iccid.encode());
    add(messageBytes, extensionStrings.encode());

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private void add(List<Byte> bytes, byte[] toAdd) {
    bytes.addAll(Arrays.asList(ArrayUtils.toObject(toAdd)));
  }

}
