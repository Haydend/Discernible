package com.discernible.message.header.options.extension;

import com.discernible.message.ByteEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptionField {

  private EncryptionSubField encryptionSubField;
  private long randomKey;

  public enum EncryptionSubField implements ByteEnum {
    NONE((byte) 0x00),
    ESN((byte) 0x01),
    IMEI_MEID((byte) 0x02),
    USER_DEFINED_MOBILE_ID((byte) 0x03);

    private final byte _byte;

    private EncryptionSubField(byte _byte) {
      this._byte = _byte;
    }

    @Override
    public byte getByteForEnum() {
      return _byte;
    }

  }
}
