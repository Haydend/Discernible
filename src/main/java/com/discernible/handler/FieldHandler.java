package com.discernible.handler;

import java.util.Queue;

public interface FieldHandler<E> {

  E decode(Queue<Byte> messageBytes);

  void encode(E field, ByteOutputStream out);

}
