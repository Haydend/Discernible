package com.discernible.handler.body.type2;

import java.util.EnumSet;

import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type2.FixStatusField;
import com.discernible.message.body.type2.FixStatusField.FixStatus;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FixStatusFieldHandler implements FieldHandler<FixStatusField> {

  @Override
  public FixStatusField decode(JBBPBitInputStream messageQueue) {
    EnumSet<FixStatus> fixStatus = FixStatus.decode(ByteUtils.getByte(messageQueue));
    return new FixStatusField(fixStatus);
  }

  @Override
  public byte[] encode(FixStatusField field) {

    int returnValue = 0;
    for (FixStatus _fixStatus : field.getFixStatus()) {
      returnValue |= _fixStatus.getValue();
    }

    return new byte[] {(byte) returnValue};
  }

}
