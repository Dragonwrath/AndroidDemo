package com.example.concurrent.basic.tutorial;


import java.util.concurrent.atomic.AtomicReference;

public class SimulatedCAS{
  private int value;
  public synchronized int get() {
    return value;
  }

  public synchronized int compareAndSwap(int expectedValue, int newValue) {
    int oldValue = value;
    if (oldValue == expectedValue) {
      value = newValue;
    }
    return oldValue;
  }

  public synchronized boolean compareAndSet(int expectedValue, int newValue) {
    return (expectedValue == compareAndSwap(expectedValue, newValue));
  }

  class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
      return value.get();
    }

    public int increment() {
      int v;
      do {
        v = value.get();
      } while (v != value.compareAndSwap(v, v +1));
      return v +1;
    }
  }

  static class CasNumberRange {
    private static class IntPair {
      final int upper;
      final int lower;

      public IntPair(int upper, int lower) {
        this.upper = upper;
        this.lower = lower;
      }
    }

    private final AtomicReference<IntPair> values =
        new AtomicReference<>(new IntPair(0, 0));

    public int getLower() {
      return values.get().lower;
    }

    public int getUpper() {
      return values.get().upper;
    }

    public void setLower(int i) {
      while (true) {
        IntPair oldV = values.get();
        if (i > oldV.upper) {
          throw new IllegalArgumentException("");
        }
        IntPair newV = new IntPair(i, oldV.upper);
        if (values.compareAndSet(oldV, newV))
          return;
      }
    }
  }
}


