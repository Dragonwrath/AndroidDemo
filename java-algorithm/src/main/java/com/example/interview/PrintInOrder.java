package com.example.interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 按照相应的格式输出指定的线程顺序输出两种格式
 * 1、线程ABC，顺序输出线程名A,B,C,A,B,C
 * 2、线程ABC, 循环输出线程名A,B,C,C,B,A
 *
 */
public class PrintInOrder {

  public static void main(String[] args) throws InterruptedException {
    doWithReversionByLock();
  }

  private static void doWithSemaphore() throws InterruptedException {

    class LinkThread extends Thread {
      private volatile boolean running;
      private Semaphore lock;
      private Semaphore nextLock;

      private LinkThread(String name) {
        super(name);
      }

      private void setLock(Semaphore lock) {
        this.lock = lock;
      }

      private void setNextLock(Semaphore lock) {
        this.nextLock = lock;
      }

      @Override
      public synchronized void start() {
        super.start();
        running = true;
      }

      @Override
      public void run() {
        running = true;
        while (running) {
          try {
            lock.acquire();
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.sleep(10);
            nextLock.release();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }

    Semaphore lockA = new Semaphore(1);
    Semaphore lockB = new Semaphore(1);
    Semaphore lockC = new Semaphore(1);

    LinkThread a = new LinkThread("A");
    LinkThread b = new LinkThread("B");
    LinkThread c = new LinkThread("C");


    a.setLock(lockA);
    a.setNextLock(lockB);

    b.setLock(lockB);
    b.setNextLock(lockC);
    c.setLock(lockC);
    c.setNextLock(lockA);

    lockA.acquire();
    lockB.acquire();
    lockC.acquire();

    a.start();
    b.start();
    c.start();
    Thread.sleep(1000);

    lockA.release();
  }

  private static void doWithObjectLock() throws InterruptedException {
    class LinkThread extends Thread {
      private volatile boolean running;
      private Object lock;
      private Object nextLock;

      private LinkThread(String name) {
        super(name);
      }

      private void setLock(Object lock) {
        this.lock = lock;
      }

      private void setNextLock(Object lock) {
        this.nextLock = lock;
      }

      @Override
      public synchronized void start() {
        super.start();
        running = true;
      }

      @Override
      public void run() {
        running = true;
        while (running) {
          try {
            synchronized (lock) {
              lock.wait();
            }
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.sleep(1000);

            synchronized (nextLock) {
              nextLock.notify();
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }

    }

    Object lockA = new Object();
    Object lockB = new Object();
    Object lockC = new Object();

    LinkThread a = new LinkThread("A");
    LinkThread b = new LinkThread("B");
    LinkThread c = new LinkThread("C");


    a.setLock(lockA);
    a.setNextLock(lockB);

    b.setLock(lockB);
    b.setNextLock(lockC);

    c.setLock(lockC);
    c.setNextLock(lockA);


    a.start();
    b.start();
    c.start();
    Thread.sleep(1000);


    synchronized (lockA) {
      lockA.notify();
    }
  }

  private static void doWithJoin() throws InterruptedException {
    class LinkThread extends Thread {
      private Thread nextThread;
      private volatile boolean running = true;

      private LinkThread(String name) {
        super(name);
      }

      private void setNextThread(Thread nextThread) {
        this.nextThread = nextThread;
      }

      @Override
      public void run() {
        while (running) {
          System.out.println(Thread.currentThread().getName());
          try {
            if (!nextThread.isAlive()) {
              nextThread.start();
            }
            Thread.sleep(1100);
            nextThread.join(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }

    LinkThread a = new LinkThread("A");
    LinkThread b = new LinkThread("B");
    LinkThread c = new LinkThread("C");
    a.setNextThread(b);
    b.setNextThread(c);
    c.setNextThread(a);

    a.start();
    a.join(1000);

  }

  private static void doWithReentrantLock() throws InterruptedException {
    final ReentrantLock Lock = new ReentrantLock();
    Condition lockA = Lock.newCondition();
    Condition lockB = Lock.newCondition();
    Condition lockC = Lock.newCondition();

    class LinkThread extends Thread {
      private volatile boolean running = true;
      private Condition lock;
      private Condition nextLock;

      private LinkThread(String name) {
        super(name);
      }

      private void setLock(Condition lock) {
        this.lock = lock;
      }

      private void setNextLock(Condition lock) {
        this.nextLock = lock;
      }

      @Override
      public void run() {
        while (running) {
          try {
            Lock.lock();
            lock.await();
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.sleep(5);
            nextLock.signal();
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            Lock.unlock();
          }
        }
      }

      private void doNotify() {
        try  {
          Lock.lock();
          lock.signal();
        } finally {
          Lock.unlock();
        }
      }
    }
    LinkThread a = new LinkThread("A");
    LinkThread b = new LinkThread("B");
    LinkThread c = new LinkThread("C");


    a.setLock(lockA);
    a.setNextLock(lockB);

    b.setLock(lockB);
    b.setNextLock(lockC);

    c.setLock(lockC);
    c.setNextLock(lockA);


    a.start();
    b.start();
    c.start();

    a.doNotify();
  }

  private static void doWithReversionByAtomic() {
    class LinkedThread extends Thread {
      private final int factorA,factorB;
      private final AtomicInteger atomicInteger;
      private volatile boolean running = true;

      private LinkedThread(String name, int factorA, int factorB, AtomicInteger atomicInteger) {
        super(name);
        this.factorA = factorA;
        this.factorB = factorB;
        this.atomicInteger = atomicInteger;
      }

      @Override
      public void run() {
        super.run();
        while (running) {
          if (atomicInteger.get() < Integer.MAX_VALUE)
            while (calculateFactor()) {
              System.out.print(Thread.currentThread().getName() + " is running");
              atomicInteger.incrementAndGet();
            }
        }
      }

      private boolean calculateFactor() {
        final int i = atomicInteger.get();
        return (i % 6 == factorA ||  i % 6 == factorB);
      }
    }
    AtomicInteger integer = new AtomicInteger(0);
    LinkedThread a = new LinkedThread("A", 0, 5, integer);
    LinkedThread b = new LinkedThread("B", 1, 4, integer);
    LinkedThread c = new LinkedThread("C", 2, 3, integer);
    a.start();
    b.start();
    c.start();
  }


  private static void doWithReversionByLock() {

    class Observer {
      private int num;

      private int getNum() {
        return num;
      }

      private void setNum(int num) {
        this.num = num;
      }
    }

    class LinkedThread extends Thread {
      private final int factorA,factorB;
      private final Observer observer;
      private volatile boolean running = true;

      private LinkedThread(String name, int factorA, int factorB, Observer observer) {
        super(name);
        this.factorA = factorA;
        this.factorB = factorB;
        this.observer = observer;
      }

      @Override
      public void run() {
        print();
      }

      private void print() {
        while (running) {
          final Thread thread = Thread.currentThread();
          //noinspection ALL
          synchronized (observer) {
            while (!calculateFactor()) {
//              System.out.println(Thread.currentThread().getName() + "wait");
              try {
                observer.wait(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
            System.out.println(Thread.currentThread().getName());
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            final int num = observer.getNum() + 1;
            observer.setNum(num);
            observer.notifyAll();
          }
        }
      }

      private boolean calculateFactor() {
          final int i = observer.getNum();
          return (i % 6 == factorA ||  i % 6 == factorB);
      }
    }

    Observer observer = new Observer();
    LinkedThread a = new LinkedThread("A", 0, 5, observer);
    LinkedThread b = new LinkedThread("B", 1, 4, observer);
    LinkedThread c = new LinkedThread("C", 2, 3, observer);
    a.start();
    b.start();
    c.start();
  }
}
