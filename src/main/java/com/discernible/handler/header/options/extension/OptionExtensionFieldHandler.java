package com.discernible.handler.header.options.extension;

import java.io.IOException;
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
import com.igormaznitsa.jbbp.JBBPParser;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;
import com.igormaznitsa.jbbp.io.JBBPOut;
import com.igormaznitsa.jbbp.model.JBBPFieldArrayUByte;
import com.igormaznitsa.jbbp.model.JBBPFieldString;
import com.igormaznitsa.jbbp.model.JBBPFieldStruct;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionExtensionFieldHandler implements FieldHandler<OptionExtension> {

  private static final JBBPParser HEADER =
      JBBPParser.prepare("skip;bit:1 esnFlag;bit:1 vinFlag;bit:1 encryptionFlag;bit:1 lmDirectCompressionFlag;bit:1 lmDirectRouting;bit:3;");
  private static final JBBPParser ESN = JBBPParser.prepare("ubyte esnLength;ubyte[(esnLength)] esn;");
  private static final JBBPParser VIN = JBBPParser.prepare("stringj vin;");

  private final ByteFieldHandler byteFieldHandler = new ByteFieldHandler();
  private final Ascii8BitFieldHandler ascii8BitFieldHandler = new Ascii8BitFieldHandler();
  private final EncryptionFieldHandler encryptionFieldHandler = new EncryptionFieldHandler();
  private final LmDirectRoutingFieldHandler lmDirectRoutingFieldHandler = new LmDirectRoutingFieldHandler();

  @Override
  public OptionExtension decode(JBBPBitInputStream messageBytes) {

    JBBPFieldStruct header = ByteUtils.parse(messageBytes, HEADER);

    byte[] esn = null;
    if (ByteUtils.isFlagSet(header, "esnFlag")) {
      esn = ByteUtils.parse(messageBytes, ESN).findFieldForNameAndType("esn", JBBPFieldArrayUByte.class).getArray();
    }

    String vin = null;
    if (ByteUtils.isFlagSet(header, "vinFlag")) {
      vin = ByteUtils.parse(messageBytes, VIN).findFieldForNameAndType("vin", JBBPFieldString.class).getAsString();
    }

    EncryptionField encryption = null;
    if (ByteUtils.isFlagSet(header, "encryptionFlag")) {
      encryption = encryptionFieldHandler.decode(messageBytes);
    }

    boolean lmDirectCompression = ByteUtils.isFlagSet(header, "lmDirectCompressionFlag");

    LmDirectRouting lmDirectRouting = null;
    if (ByteUtils.isFlagSet(header, "lmDirectRouting")) {
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
      byte[] vin = null;
      try {
        vin = JBBPOut.BeginBin().String(optionExtension.getVin()).End().toByteArray();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      // byte[] vin = ascii8BitFieldHandler.encode(optionExtension.getVin());
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
