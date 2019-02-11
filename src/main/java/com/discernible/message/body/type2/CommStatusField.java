package com.discernible.message.body.type2;

import java.util.Queue;

import com.discernible.message.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommStatusField implements Field {

  private boolean available;
  private boolean networkService;
  private boolean dataService;
  private boolean connected;
  private boolean voiceCallActive;
  private boolean roaming;
  private NetworkTechnology networkTechnology;

  public static CommStatusField decode(Queue<Byte> messageQueue) {
    byte fieldByte = messageQueue.poll();

    boolean available = (fieldByte & 0b00000001) != 0;
    boolean networkService = (fieldByte & 0b00000010) != 0;
    boolean dataService = (fieldByte & 0b00000100) != 0;
    boolean connected = (fieldByte & 0b00001000) != 0;
    boolean voiceCallActive = (fieldByte & 0b00010000) != 0;
    boolean roaming = (fieldByte & 0b00100000) != 0;

    final NetworkTechnology networkTechnology;
    if ((fieldByte & 0b11000000) == 0b00000000) {
      networkTechnology = NetworkTechnology._2G_NETWORK;

    } else if ((fieldByte & 0b11000000) == 0b01000000) {
      networkTechnology = NetworkTechnology._3G_NETWORK;

    } else if ((fieldByte & 0b11000000) == 0b10000000) {
      networkTechnology = NetworkTechnology._4G_NETWORK;

    } else {
      throw new IllegalArgumentException("Network Technology not supported");
    }

    return new CommStatusField(available, networkService, dataService, connected, voiceCallActive, roaming, networkTechnology);
  }

  @Override
  public byte[] encode() {
    StringBuilder bits = new StringBuilder("00000000");

    if (available) {
      bits.replace(7, 8, "1");
    }

    if (networkService) {
      bits.replace(6, 7, "1");
    }

    if (dataService) {
      bits.replace(5, 6, "1");
    }

    if (connected) {
      bits.replace(4, 5, "1");
    }

    if (voiceCallActive) {
      bits.replace(3, 4, "1");
    }

    if (roaming) {
      bits.replace(2, 3, "1");
    }

    switch (networkTechnology) {
    case _2G_NETWORK:
      bits.replace(0, 2, "00");
      break;
    case _3G_NETWORK:
      bits.replace(0, 2, "01");
      break;
    case _4G_NETWORK:
      bits.replace(0, 2, "10");
      break;
    default:
      throw new IllegalArgumentException("Network Technology not supported");
    }

    return new byte[] { Byte.valueOf(bits.toString(), 2) };
  }

  public enum NetworkTechnology {
    _2G_NETWORK,
    _3G_NETWORK,
    _4G_NETWORK;
  }

}
