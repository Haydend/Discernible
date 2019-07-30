package com.discernible.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class ByteOutputStream extends ByteArrayOutputStream {

  public void write(byte b[]) {
    try {
      super.write(b);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void writeUnsignedInt(Long val) {
    byte[] messageBytes = new byte[4];
    byte[] randomKeyBytes = BigInteger.valueOf(val).toByteArray();
    int padding = 4 - randomKeyBytes.length;
    System.arraycopy(randomKeyBytes, 0, messageBytes, padding, randomKeyBytes.length);
    write(messageBytes);
  }

  public void writeUnsignedShort(Integer val) {
    byte[] messageBytes = new byte[2];
    byte[] randomKeyBytes = BigInteger.valueOf(val).toByteArray();
    int padding = 2 - randomKeyBytes.length;
    System.arraycopy(randomKeyBytes, 0, messageBytes, padding, randomKeyBytes.length);
    write(messageBytes);
  }

  public synchronized byte toByteArray()[] {
    try {
      super.flush();
      return super.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
