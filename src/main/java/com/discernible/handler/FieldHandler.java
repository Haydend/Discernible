package com.discernible.handler;

import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public interface FieldHandler<E> {

  E decode(JBBPBitInputStream messageBytes);

  byte[] encode(E field);

}
