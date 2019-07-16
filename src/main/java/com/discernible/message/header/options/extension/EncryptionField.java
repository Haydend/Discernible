package com.discernible.message.header.options.extension;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptionField {

  private EncryptionSubField encryptionSubField;
  private long randomKey;

  public enum EncryptionSubField {
    NONE,
    ESN,
    IMEI_MEID,
    USER_DEFINED_MOBILE_ID
  }
}
