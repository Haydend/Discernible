package com.discernible.handler.header.options.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.handler.Ascii8BitFieldHandler;
import com.discernible.handler.ByteFieldHandler;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.LmDirectRouting;
import com.discernible.message.header.options.extension.OptionExtension;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionExtensionFieldHandler implements FieldHandler<OptionExtension> {

  private final ByteFieldHandler byteFieldHandler = new ByteFieldHandler();
  private final Ascii8BitFieldHandler ascii8BitFieldHandler = new Ascii8BitFieldHandler();
  private final EncryptionFieldHandler encryptionFieldHandler = new EncryptionFieldHandler();
  private final LmDirectRoutingFieldHandler lmDirectRoutingFieldHandler = new LmDirectRoutingFieldHandler();

  @Override
  public OptionExtension decode(JBBPBitInputStream messageBytes) {

    ByteUtils.getByte(messageBytes); // Throw away field length;

    byte flagByte = ByteUtils.getByte(messageBytes);

    byte[] esn = null;
    if ((flagByte & 0b00000001) == 0b00000001) {
      esn = byteFieldHandler.decode(messageBytes);
    }

    String vin = null;
    if ((flagByte & 0b00000010) == 0b00000010) {
      vin = ascii8BitFieldHandler.decode(messageBytes);
    }

    EncryptionField encryption = null;
    if ((flagByte & 0b00000100) == 0b00000100) {
      encryption = encryptionFieldHandler.decode(messageBytes);
    }

    boolean lmDirectCompression = (flagByte & 0b00001000) == 0b00001000;

    LmDirectRouting lmDirectRouting = null;
    if ((flagByte & 0b00010000) == 0b00010000) {
      lmDirectRouting = lmDirectRoutingFieldHandler.decode(messageBytes);
    }

    return new OptionExtension(esn, vin, encryption, lmDirectCompression, lmDirectRouting);
  }

  @Override
  public byte[] encode(OptionExtension optionExtension) {

    List<Byte> messageBytes = new ArrayList<>();
    messageBytes.add(new Byte((byte) 0x01));
    messageBytes.add(buildFlagByte(optionExtension));

    if (Objects.nonNull(optionExtension.getEsn())) {
      byte[] esn = byteFieldHandler.encode(optionExtension.getEsn());
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(esn)));
    }

    if (Objects.nonNull(optionExtension.getVin())) {
      byte[] vin = ascii8BitFieldHandler.encode(optionExtension.getVin());
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(vin)));
    }

    if (Objects.nonNull(optionExtension.getEncryption())) {
      byte[] encryption = encryptionFieldHandler.encode(optionExtension.getEncryption());
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(encryption)));
    }

    if (Objects.nonNull(optionExtension.getLmDirectRouting())) {
      byte[] lmDirectRouting = lmDirectRoutingFieldHandler.encode(optionExtension.getLmDirectRouting());
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(lmDirectRouting)));
    }

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private byte buildFlagByte(OptionExtension optionExtension) {

    StringBuilder bits = new StringBuilder("00000000");

    if (Objects.nonNull(optionExtension.getEsn())) {
      bits.replace(7, 8, "1");
    }

    if (Objects.nonNull(optionExtension.getVin())) {
      bits.replace(6, 7, "1");
    }

    if (Objects.nonNull(optionExtension.getEncryption())) {
      bits.replace(5, 6, "1");
    }

    if (optionExtension.isLmDirectCompression()) {
      bits.replace(4, 5, "1");
    }

    if (Objects.nonNull(optionExtension.getLmDirectRouting())) {
      bits.replace(3, 4, "1");
    }

    return Byte.valueOf(bits.toString(), 2);
  }

}
