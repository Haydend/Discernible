package com.discernible.message.header.options;

import lombok.Data;

@Data
public class EncryptionService {

  private EncryptionSubField encryptionSubField;
  private byte[] randomKey;

  public enum EncryptionSubField {
    NONE,
    ESN,
    IMEI_MEID,
    USER_DEFINED_MOBILE_ID
  }

}
