package com.discernible.handler.body.type1;

import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type1.StatusField;
import com.discernible.message.body.type1.StatusField.Status;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusFieldHandler implements FieldHandler<StatusField> {

  @Override
  public StatusField decode(Queue<Byte> messageBytes) {

    byte statusByte = messageBytes.poll();
    Status status = Status.values()[(int) statusByte];

    return new StatusField(status);
  }

  @Override
  public void encode(StatusField field, ByteOutputStream output) {
    output.write(field.getStatus().ordinal());
  }

}
