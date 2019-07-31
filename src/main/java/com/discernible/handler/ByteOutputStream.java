package com.discernible.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteOutputStream extends ByteArrayOutputStream {

  public void write(byte b[]) {
    try {
      super.write(b);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
