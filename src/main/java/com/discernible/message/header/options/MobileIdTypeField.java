package com.discernible.message.header.options;

import java.util.Queue;

import com.discernible.message.Field;
import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MobileIdTypeField implements Field {

  private MobileIdType mobileIdType;

  public static MobileIdTypeField decode(Queue<Byte> messageBytes) {

    messageBytes.poll(); // Throw away field length.

    MobileIdType mobileIdType = MobileIdType.values()[messageBytes.poll()];

    return new MobileIdTypeField(mobileIdType);
  }

  @Override
  public byte[] encode() {

    byte[] messageBytes = new byte[1];
    messageBytes[0] = (byte) mobileIdType.ordinal();

    return ByteUtils.prependFieldLength(messageBytes);
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
