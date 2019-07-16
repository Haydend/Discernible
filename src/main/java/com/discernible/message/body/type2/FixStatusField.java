package com.discernible.message.body.type2;

import java.util.EnumSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class FixStatusField {

  private EnumSet<FixStatus> fixStatus;

  public enum FixStatus {
    PREDICTED(1),
    DIFFERENTIALLY_CORRECTED(2),
    LAST_KNOWN(4),
    INVALID_FIX(8),
    _2D_FIX(16),
    HISTORIC(32),
    INVALID_TIME(64);

    @Getter
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
