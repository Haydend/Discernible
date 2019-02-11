package com.discernible.message.body.type2;

import java.util.EnumSet;
import java.util.Queue;

import com.discernible.message.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FixStatusField implements Field {

  private EnumSet<FixStatus> fixStatus;

  public static FixStatusField decode(Queue<Byte> messageQueue) {
    EnumSet<FixStatus> fixStatus = FixStatus.decode(messageQueue.poll());
    return new FixStatusField(fixStatus);
  }

  @Override
  public byte[] encode() {

    int returnValue = 0;
    for (FixStatus _fixStatus : fixStatus) {
      returnValue |= _fixStatus.value;
    }

    return new byte[] { (byte) returnValue };
  }

  public enum FixStatus {
    PREDICTED(1),
    DIFFERENTIALLY_CORRECTED(2),
    LAST_KNOWN(4),
    INVALID_FIX(8),
    _2D_FIX(16),
    HISTORIC(32),
    INVALID_TIME(64);

    private int value;

    private FixStatus(int value) {
      this.value = value;
    }

    public static EnumSet<FixStatus> decode(byte _byte) {
      EnumSet<FixStatus> codesList = EnumSet.noneOf(FixStatus.class);
      for (FixStatus code : values()) {
        if ((_byte & code.value) != 0) {
          codesList.add(code);
        }
      }
      return codesList;
    }
  }

}
