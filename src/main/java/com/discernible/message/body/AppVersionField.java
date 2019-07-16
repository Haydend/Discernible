package com.discernible.message.body;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppVersionField {

  private int version;
  private char subVersion;

}
