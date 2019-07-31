package com.discernible.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteInputStream extends ByteArrayInputStream {

  public ByteInputStream(byte[] buf) {
    super(buf);
  }

  public byte[] read(int length) {
    try {
      byte[] bytes = new byte[length];
      read(bytes);
      return bytes;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public byte[] readAll() {
    return read(available());
  }

}
