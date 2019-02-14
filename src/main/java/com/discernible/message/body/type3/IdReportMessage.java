package com.discernible.message.body.type3;

import java.util.Queue;

import com.discernible.message.UnsignedShortField;
import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.MessageBody;
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
  private UnsignedShortField queryId;
  private EsnField esn;

  public static IdReportMessage decodeBody(Queue<Byte> messageBytes, ServiceType serviceType) {

    UnsignedShortField scriptVersion = UnsignedShortField.decode(messageBytes);
    UnsignedShortField configVersion = UnsignedShortField.decode(messageBytes);
    AppVersionField firmwareVersion = AppVersionField.decode(messageBytes);
    UnsignedShortField vehicleClass = UnsignedShortField.decode(messageBytes);
    UnitStatusField unitStatus = UnitStatusField.decode(messageBytes);
    UnsignedShortField modemSelection = UnsignedShortField.decode(messageBytes);
    UnsignedShortField applicationId = UnsignedShortField.decode(messageBytes);
    UnsignedShortField mobileId = UnsignedShortField.decode(messageBytes);
    UnsignedShortField queryId = UnsignedShortField.decode(messageBytes);
    EsnField esn = EsnField.decode(messageBytes);

    return new IdReportMessage(serviceType, scriptVersion, configVersion, firmwareVersion, vehicleClass, unitStatus, modemSelection, applicationId,
        mobileId, queryId, esn);
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
    return null;
  }

}
