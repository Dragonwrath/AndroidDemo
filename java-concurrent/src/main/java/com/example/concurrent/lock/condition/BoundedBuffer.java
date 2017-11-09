package com.example.concurrent.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBuffer<T> {
  private final ReentrantLock lock;
  private final Condition notFull;
  private final Condition notEmpty;

  private final T[] items =  (T[])(new Object[100]);
  private int putptr, takeptr, count;

  BoundedBuffer() {
    lock = new ReentrantLock(true);
    notEmpty = lock.newCondition();
    notFull = lock.newCondition();
  }

  public void put(T x) throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      while (count == items.length) {
        notFull.await();
      }
      items[putptr] = x;
      if (++putptr == items.length) putptr = 0;
      ++count;
      notEmpty.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public T take() throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      T x = items[takeptr];
      if (++takeptr == items.length) takeptr = 0;
      --count;
      notFull.signal();
      return x;
    } finally {
      lock.unlock();
    }
  }

  int size() throws InterruptedException {
    try {
      lock.lockInterruptibly();
      return count;
    } finally {
      lock.unlock();
    }
  }
}
