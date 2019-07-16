package com.discernible.message.header.options;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MobileIdTypeField {

  private MobileIdType mobileIdType;

  public enum MobileIdType {
    OFF,
    ESN,
    IMEI_OR_EID,
    IMSI,
    USER_DEFINED,
    PHONE_NUMBER,
    IP,
    MEID_IMEI;
  }
}
