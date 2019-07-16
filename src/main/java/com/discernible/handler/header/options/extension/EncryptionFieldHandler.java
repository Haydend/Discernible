package com.discernible.handler.header.options.extension;

import java.math.BigInteger;
import java.util.Queue;

import com.discernible.handler.FieldHandler;
import com.discernible.message.header.options.extension.EncryptionField;
import com.discernible.message.header.options.extension.EncryptionField.EncryptionSubField;
import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptionFieldHandler implements FieldHandler<EncryptionField> {

  @Override
  public EncryptionField decode(Queue<Byte> messageBytes) {

    messageBytes.poll(); // Throw away field length.
    messageBytes.poll(); // Throw away the version and theirs only one version.

    EncryptionSubField encryptionSubField = EncryptionSubField.values()[messageBytes.poll()];

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
