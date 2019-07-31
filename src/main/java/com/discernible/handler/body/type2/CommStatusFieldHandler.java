package com.discernible.handler.body.type2;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type2.CommStatusField;
import com.discernible.message.body.type2.CommStatusField.NetworkTechnology;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommStatusFieldHandler implements FieldHandler<CommStatusField> {

  @Override
  public CommStatusField decode(ByteInputStream in) {
    byte fieldByte = (byte) in.read();

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
  public void encode(CommStatusField field, ByteOutputStream out) {
    StringBuilder bits = new StringBuilder("00000000");

    if (field.isAvailable()) {
      bits.replace(7, 8, "1");
    }

    if (field.isNetworkService()) {
      bits.replace(6, 7, "1");
    }

    if (field.isDataService()) {
      bits.replace(5, 6, "1");
    }

    if (field.isConnected()) {
      bits.replace(4, 5, "1");
    }

    if (field.isVoiceCallActive()) {
      bits.replace(3, 4, "1");
    }

    if (field.isRoaming()) {
      bits.replace(2, 3, "1");
    }

    switch (field.getNetworkTechnology()) {
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

    out.write(Byte.valueOf(bits.toString(), 2));
  }

}
