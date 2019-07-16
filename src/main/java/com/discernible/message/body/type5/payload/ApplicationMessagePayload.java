package com.discernible.message.body.type5.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class ApplicationMessagePayload {

  private Integer applicationMessageType;


}
