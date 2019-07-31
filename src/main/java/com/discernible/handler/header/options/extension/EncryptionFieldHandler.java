package com.discernible.handler.header.options.extension;

import com.discernible.handler.ByteInputStream;
import com.discernible.handler.ByteOutputStream;
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
  public EncryptionField decode(ByteInputStream in) {

    in.read(); // Throw away field length.
    in.read(); // Throw away the version and theirs only one version.

    EncryptionSubField encryptionSubField = EncryptionSubField.values()[in.read()];

    byte[] randomKeyBytes = in.read(4);
    long randomKey = ByteUtils.unsignedIntToLong(randomKeyBytes);

    return new EncryptionField(encryptionSubField, randomKey);
  }

  @Override
  public void encode(EncryptionField encryptionField, ByteOutputStream out) {
    out.write(0x06);
    out.write(0x01);
    out.write(encryptionField.getEncryptionSubField().ordinal());
    out.writeUnsignedInt(encryptionField.getRandomKey());
  }

}
