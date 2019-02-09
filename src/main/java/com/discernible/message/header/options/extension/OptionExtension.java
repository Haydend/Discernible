package com.discernible.message.header.options.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.message.Ascii8BitField;
import com.discernible.message.ByteField;
import com.discernible.message.Field;
import com.discernible.message.WithLength;

import lombok.Data;

@Data
public class OptionExtension implements Field {

  private final WithLength<ByteField> esn;
  private final WithLength<Ascii8BitField> vin;
  private final WithLength<EncryptionField> encryption;
  private final Boolean lmDirectCompression;
  private final WithLength<LmDirectRouting> lmDirectRouting;

  @Override
  public byte[] encode() {

    List<Byte> messageBytes = new ArrayList<>();
    messageBytes.add(new Byte((byte) 0x01));
    messageBytes.add(buildFlagByte());

    if (Objects.nonNull(esn)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(esn.encode())));
    }

    if (Objects.nonNull(vin)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(vin.encode())));
    }

    if (Objects.nonNull(encryption)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(encryption.encode())));
    }

    if (Objects.nonNull(lmDirectRouting)) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(lmDirectRouting.encode())));
    }

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private byte buildFlagByte() {

    StringBuilder bits = new StringBuilder("00000000");

    if (Objects.nonNull(esn)) {
      bits.replace(7, 8, "1");
    }

    if (Objects.nonNull(vin)) {
      bits.replace(6, 7, "1");
    }

    if (Objects.nonNull(encryption)) {
      bits.replace(5, 6, "1");
    }

    if (Objects.nonNull(lmDirectCompression)) {
      bits.replace(4, 5, "1");
    }

    if (Objects.nonNull(lmDirectRouting)) {
      bits.replace(3, 4, "1");
    }

    return Byte.valueOf(bits.toString(), 2);
  }

}
