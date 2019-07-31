package com.discernible.handler.header.options.extension;

import java.util.Objects;

import com.discernible.handler.Ascii8BitFieldHandler;
import com.discernible.handler.ByteFieldHandler;
import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.LmDirectRouting;
import com.discernible.message.header.options.extension.OptionExtension;

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
  public OptionExtension decode(ByteInputStream in) {

    in.read(); // Throw away field length;

    byte flagByte = (byte) in.read();

    byte[] esn = null;
    if ((flagByte & 0b00000001) == 0b00000001) {
      esn = byteFieldHandler.decode(in);
    }

    String vin = null;
    if ((flagByte & 0b00000010) == 0b00000010) {
      vin = ascii8BitFieldHandler.decode(in);
    }

    EncryptionField encryption = null;
    if ((flagByte & 0b00000100) == 0b00000100) {
      encryption = encryptionFieldHandler.decode(in);
    }

    boolean lmDirectCompression = (flagByte & 0b00001000) == 0b00001000;

    LmDirectRouting lmDirectRouting = null;
    if ((flagByte & 0b00010000) == 0b00010000) {
      lmDirectRouting = lmDirectRoutingFieldHandler.decode(in);
    }

    return new OptionExtension(esn, vin, encryption, lmDirectCompression, lmDirectRouting);
  }

  @Override
  public void encode(OptionExtension optionExtension, ByteOutputStream out) {

    out.write(0x01);
    out.write(buildFlagByte(optionExtension));

    if (Objects.nonNull(optionExtension.getEsn())) {
      byteFieldHandler.encode(optionExtension.getEsn(), out);
    }

    if (Objects.nonNull(optionExtension.getVin())) {
      ascii8BitFieldHandler.encode(optionExtension.getVin(), out);
    }

    if (Objects.nonNull(optionExtension.getEncryption())) {
      encryptionFieldHandler.encode(optionExtension.getEncryption(), out);
    }

    if (Objects.nonNull(optionExtension.getLmDirectRouting())) {
      lmDirectRoutingFieldHandler.encode(optionExtension.getLmDirectRouting(), out);
    }
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
