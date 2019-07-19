package com.discernible.handler.body.type1;

import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type1.StatusField;
import com.discernible.message.body.type1.StatusField.Status;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusFieldHandler implements FieldHandler<StatusField> {

  @Override
  public StatusField decode(JBBPBitInputStream messageBytes) {

    byte statusByte = ByteUtils.getByte(messageBytes);
    Status status = Status.values()[(int) statusByte];

    return new StatusField(status);
  }

  @Override
  public byte[] encode(StatusField field) {
    byte statusByte = (byte) field.getStatus().ordinal();
    return new byte[] {statusByte};
  }

}
