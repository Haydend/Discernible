package com.discernible.message.body.type5.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MotionLogReport extends ApplicationMessagePayload {

  public MotionLogReport() {
    super(122);
  }

}
