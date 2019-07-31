package com.discernible.handler.body.type2;

import java.util.EnumSet;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type2.FixStatusField;
import com.discernible.message.body.type2.FixStatusField.FixStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FixStatusFieldHandler implements FieldHandler<FixStatusField> {

  @Override
  public FixStatusField decode(ByteInputStream in) {
    EnumSet<FixStatus> fixStatus = FixStatus.decode((byte) in.read());
    return new FixStatusField(fixStatus);
  }

  @Override
  public void encode(FixStatusField field, ByteOutputStream out) {

    int returnValue = 0;
    for (FixStatus _fixStatus : field.getFixStatus()) {
      returnValue |= _fixStatus.getValue();
    }

    out.write(returnValue);
  }

}
