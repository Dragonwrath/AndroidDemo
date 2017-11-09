package com.example.concurrent.lock.condition;


import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConditionTest {
  public static void main(String[] args) {
    try {
      runnableTest();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void runnableTest() throws InterruptedException {
    long t1 = System.currentTimeMillis();

    BlockingQueue<String> buffer = new ArrayBlockingQueue<>(100);
    Producer producer = new Producer(buffer);
    Consumer consumer = new Consumer(buffer);

    for (int i = 0; i < 5; i++) {
      new Thread(producer).start();
    }
    for (int i = 0; i < 6; i++) {
      new Thread(consumer).start();
    }

    for (;;) {
      if (System.currentTimeMillis() - t1 > 10000) {
        producer.quit();
        consumer.quit();
        break;
      }
    }
  }
}

class Producer  implements Runnable {
  private BlockingQueue<String> buffer;
  private volatile boolean running;

  Producer(BlockingQueue<String> buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    running = true;
    while (running)
      try {
        String x = new Date().toString();
        buffer.put(x);
        System.out.println(x + "----------" + buffer.size() + "----------" + Thread.currentThread().getName());
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
  }

  synchronized void quit() {
    running = false;
  }

}

class Consumer implements Runnable {
  private BlockingQueue<String> buffer;
  private volatile boolean running;

  Consumer(BlockingQueue<String> buffer) {
    this.buffer = buffer;
  }

  public void run() {
    running = true;
    while (running)
    try {
      String take = buffer.take();
      System.out.println(buffer.size() + "----------" + take + "----------" + Thread.currentThread().getName());
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  synchronized void quit() {
    running = false;
  }
}



