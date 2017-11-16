package com.example.concurrent.lock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Basic {
  public static void main(String[] args) throws InterruptedException {
    Something something = new Something();
    ProduceRunnable produce = new ProduceRunnable(something);
    ConsumeRunnable consume = new ConsumeRunnable(something);
    for (int i = 0; i < 3; i++) {
      Thread thread = new Thread(produce);
      thread.start();
      thread.join(3000);
    }
    for (int i = 0; i < 2; i++) {
      Thread thread = new Thread(consume);
      thread.start();
      thread.join(3000);
    }

    System.out.println(something.getSize());

    Thread.sleep(5000);
    something.print();
  }

  private static class ProduceRunnable implements Runnable {
    private final Something some;
    private volatile int i;

    ProduceRunnable(Something some) {
      this.some = some;
    }

    @Override
    public void run() {
      some.produce(String.valueOf(++i));
    }
  }

  private static class ConsumeRunnable implements Runnable {
    private final Something some;

    ConsumeRunnable(Something some) {
      this.some = some;
    }

    @Override
    public void run() {
      some.consume();
    }
  }

  private static class Something {
    private final Object notFull = new Object();
    private final Object notEmpty = new Object();
    private final String[] list = new String[100];
    private int index, count;

    private void produce(String string) {
      synchronized (notFull) {
        try {
          notFull.wait(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      try {
        list[index] = string;
        index++;
        if (index > 100) {
          index = 0;
        }
        Thread.sleep(100);
        System.out.println("produce " + string);
        notEmpty.notify();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

    private String consume() {
      synchronized (notEmpty) {
        try {
          notEmpty.wait(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      try {
        String s = list[index];
        list[index] = null;
        index--;
        if (index < 0) {
          index = 0;
        }
        Thread.sleep(100);
        notFull.notify();
        System.out.println("consume " + s);
        return s;
      } catch (InterruptedException e) {
        e.printStackTrace();
        return null;
      }
    }

    private int getSize() {
      String[] copy = Arrays.copyOf(list, list.length);
      for (int i = 0; i < copy.length; i++) {
        if (copy[i] == null)
          return i;
      }
      return copy.length;
    }

    private void print() {
      for (String s : list) {
        if (s != null) {
          System.out.println(s);
        } else {
          break;
        }
      }
    }
  }

  private static void doubleTryLock() {
    Lock lock = new ReentrantLock();
    while (!Thread.currentThread().isInterrupted()) {
      if (lock.tryLock()) {
        try {
          if (lock.tryLock()) {
            //do something
          }
        } finally {
          lock.unlock();
        }
      }
      //some broken situation
    }
  }

  private static final ReentrantLock lock = new ReentrantLock();

  private static void stateDependentMethod() throws InterruptedException{
    //必须通过一个条件来保护条件谓词
    synchronized (lock) {
      while (!conditionPredicate()) {
        lock.wait();
      }
      //现在对象处于合适的状态
    }
  }

  private static boolean conditionPredicate() {
    return false;
  }
}
