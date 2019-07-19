package com.discernible.handler.body.type2;

import com.discernible.handler.FieldHandler;
import com.discernible.message.body.type2.InputField;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputFieldHandler implements FieldHandler<InputField> {

  @Override
  public InputField decode(JBBPBitInputStream messageQueue) {
    byte fieldByte = ByteUtils.getByte(messageQueue);

    boolean ignition = (fieldByte & 0b00000001) != 0;
    boolean inputOne = (fieldByte & 0b00000010) != 0;
    boolean inputTwo = (fieldByte & 0b00000100) != 0;
    boolean inputThree = (fieldByte & 0b00001000) != 0;
    boolean inputFour = (fieldByte & 0b00010000) != 0;
    boolean inputFive = (fieldByte & 0b00100000) != 0;
    boolean inputSix = (fieldByte & 0b01000000) != 0;
    boolean inputSeven = (fieldByte & 0b10000000) != 0;

    return new InputField(ignition, inputOne, inputTwo, inputThree, inputFour, inputFive, inputSix, inputSeven);
  }

  @Override
  public byte[] encode(InputField field) {

    byte fieldByte = (byte) 0b00000000;

    if (field.isIgnition()) {
      fieldByte = (byte) (fieldByte | 0b00000001);
    }

    if (field.isInputOne()) {
      fieldByte = (byte) (fieldByte | 0b00000010);
    }

    if (field.isInputTwo()) {
      fieldByte = (byte) (fieldByte | 0b00000100);
    }

    if (field.isInputThree()) {
      fieldByte = (byte) (fieldByte | 0b00001000);
    }

    if (field.isInputFour()) {
      fieldByte = (byte) (fieldByte | 0b00010000);
    }

    if (field.isInputFive()) {
      fieldByte = (byte) (fieldByte | 0b00100000);
    }

    if (field.isInputSix()) {
      fieldByte = (byte) (fieldByte | 0b01000000);
    }

    if (field.isInputSeven()) {
      fieldByte = (byte) (fieldByte | 0b10000000);
    }

    return new byte[] {fieldByte};
  }
}
