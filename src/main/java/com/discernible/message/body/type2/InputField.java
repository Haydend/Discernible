package com.discernible.message.body.type2;

import java.util.Queue;

import com.discernible.message.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputField implements Field {

  private boolean ignition;
  private boolean inputOne;
  private boolean inputTwo;
  private boolean inputThree;
  private boolean inputFour;
  private boolean inputFive;
  private boolean inputSix;
  private boolean inputSeven;

  public static InputField decode(Queue<Byte> messageQueue) {
    byte fieldByte = messageQueue.poll();

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
  public byte[] encode() {

    byte fieldByte = (byte) 0b00000000;

    if (ignition) {
      fieldByte = (byte) (fieldByte | 0b00000001);
    }

    if (inputOne) {
      fieldByte = (byte) (fieldByte | 0b00000010);
    }

    if (inputTwo) {
      fieldByte = (byte) (fieldByte | 0b00000100);
    }

    if (inputThree) {
      fieldByte = (byte) (fieldByte | 0b00001000);
    }

    if (inputFour) {
      fieldByte = (byte) (fieldByte | 0b00010000);
    }

    if (inputFive) {
      fieldByte = (byte) (fieldByte | 0b00100000);
    }

    if (inputSix) {
      fieldByte = (byte) (fieldByte | 0b01000000);
    }

    if (inputSeven) {
      fieldByte = (byte) (fieldByte | 0b10000000);
    }

    return new byte[] {fieldByte};
  }
}
