package com.discernible.message.body.type2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommStatusField {

  private boolean available;
  private boolean networkService;
  private boolean dataService;
  private boolean connected;
  private boolean voiceCallActive;
  private boolean roaming;
  private NetworkTechnology networkTechnology;

  public enum NetworkTechnology {
    _2G_NETWORK,
    _3G_NETWORK,
    _4G_NETWORK;
  }

}
