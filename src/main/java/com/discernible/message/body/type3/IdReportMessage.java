package com.discernible.message.body.type3;

import java.util.Map;

import com.discernible.message.body.AppVersionField;
import com.discernible.message.body.Message;
import com.discernible.message.body.UnitStatusField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IdReportMessage extends Message {

  private ServiceType serviceType;
  private Short scriptVersion;
  private Short configVersion;
  private AppVersionField firmwareVersion;
  private Short vehicleClass;
  private UnitStatusField unitStatus;
  private Short modemSelection;
  private Short applicationId;
  private Short mobileId;
  private Integer queryId;
  private String esn;
  private String imei;
  private String imsi;
  private String phoneNo;
  private String iccid;
  private Map<String, String> extensionStrings;

  @Override
  public ServiceType getServiceType() {
    return serviceType;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.ID_REPORT_MESSAGE;
  }

}
