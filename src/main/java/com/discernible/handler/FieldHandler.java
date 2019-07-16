package com.discernible.handler;

import java.util.Queue;

public interface FieldHandler<E> {

  E decode(Queue<Byte> messageBytes);

  byte[] encode(E field);

}
