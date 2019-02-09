package com.discernible.message;

public interface Field {

  byte[] encode();

  @SuppressWarnings("unchecked")
  default <F extends Field> WithLength<F> withLength() {
    return new WithLength<F>((F) this);
  }

}
