package com.discernible.handler.header.options.extension;

import java.math.BigInteger;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.EncryptionField.EncryptionSubField;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptionFieldHandler implements FieldHandler<EncryptionField> {

  @Override
  public EncryptionField decode(JBBPBitInputStream messageBytes) {

    ByteUtils.getByte(messageBytes); // Throw away field length.
    ByteUtils.getByte(messageBytes); // Throw away the version and there is only one version.

    EncryptionSubField encryptionSubField = EncryptionSubField.values()[ByteUtils.getByte(messageBytes)];

    byte[] randomKeyBytes = ByteUtils.getFieldBytes(4, messageBytes);
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
