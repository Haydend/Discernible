package com.discernible.message.header.options.extension;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionExtension {

  private byte[] esn;
  private String vin;
  private EncryptionField encryption;
  private boolean lmDirectCompression;
  private LmDirectRouting lmDirectRouting;

}
