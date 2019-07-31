package com.discernible.util;

public class TestUtils {

  private TestUtils() {
  };

  @SuppressWarnings("unchecked")
  public static <E extends Exception> E catchException(RunnableThrowsException method) {
    try {
      method.run();
    } catch (Exception e) {
      return (E) e;
    }

    throw new IllegalStateException("Method did not throw an exception");
  }

}
