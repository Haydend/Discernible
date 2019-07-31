package com.discernible.handler;

public interface FieldHandler<E> {

  E decode(ByteInputStream in);

  void encode(E field, ByteOutputStream out);

}
