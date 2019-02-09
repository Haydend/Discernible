package com.discernible.message.header.options;

import com.discernible.message.Field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MobileIdTypeField implements Field {

  private final MobileIdType mobileIdType;

  @Override
  public byte[] encode() {

    byte[] messageBytes = new byte[1];
    messageBytes[0] = (byte) mobileIdType.ordinal();

    return messageBytes;
  }

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
