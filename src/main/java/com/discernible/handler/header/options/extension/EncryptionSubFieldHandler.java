package com.discernible.handler.header.options.extension;

import java.math.BigInteger;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.EncryptionField.EncryptionSubField;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.JBBPParser;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;
import com.igormaznitsa.jbbp.model.JBBPFieldArrayByte;
import com.igormaznitsa.jbbp.model.JBBPFieldByte;
import com.igormaznitsa.jbbp.model.JBBPFieldStruct;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptionSubFieldHandler implements FieldHandler<EncryptionField> {

  private static final JBBPParser ENCRYPTION = JBBPParser.prepare("skip;skip;byte encryptionSubField;byte[4] randomKey;");

  @Override
  public EncryptionField decode(JBBPBitInputStream messageBytes) {

    JBBPFieldStruct encryption = ByteUtils.parse(messageBytes, ENCRYPTION);


    int encryptionSubFieldIndex = encryption.findFieldForNameAndType("encryptionSubField", JBBPFieldByte.class).getAsInt();
    EncryptionSubField encryptionSubField = EncryptionSubField.values()[encryptionSubFieldIndex];

    byte[] randomKeyBytes = encryption.findFieldForNameAndType("randomKey", JBBPFieldArrayByte.class).getArray();
    long randomKey = ByteUtils.unsignedIntToLong(randomKeyBytes);

    return new EncryptionField(encryptionSubField, randomKey);
  }

  @Override
  public byte[] encode(EncryptionField encryptionField) {

    byte[] messageBytes = new byte[6];

    messageBytes[0] = 0x01;

    messageBytes[1] = (byte) encryptionField.getEncryptionSubField().ordinal();

    byte[] randomKeyBytes = BigInteger.valueOf(encryptionField.getRandomKey()).toByteArray();
    int padding = 4 - randomKeyBytes.length;
    System.arraycopy(randomKeyBytes, 0, messageBytes, 2 + padding, randomKeyBytes.length);

    return ByteUtils.prependFieldLength(messageBytes);
  }

}
