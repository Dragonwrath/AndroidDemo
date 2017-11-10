package com.example.concurrent.atresia;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

  public static void main(String[] args) {
    doWithRunnable();
  }

  private static void doWithRunnable() {
    Semaphore semaphore = new Semaphore(3);
    CountDownLatch latch = new CountDownLatch(10);
    ExecutorService produceService = Executors.newFixedThreadPool(3);
    long t1 = System.currentTimeMillis();
    for (int i = 0; i < 10; i++) {
      produceService.submit(new SemaphoreRunnable(semaphore, latch));
    }
    try {
      latch.await();
      long t2 = System.currentTimeMillis();
      System.out.println("Time that mission has cost is " + (t2 -t1));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static class SemaphoreRunnable implements Runnable {
    final Semaphore semaphore;
    final Random random;
    private final CountDownLatch latch;

    SemaphoreRunnable(Semaphore semaphore, CountDownLatch latch) {
      this.semaphore = semaphore;
      this.latch = latch;
      random = new Random();
    }

    @Override
    public void run() {
      boolean acquire = false;
      try {
        semaphore.acquire();
        long t1 = System.currentTimeMillis();
        acquire = true;
        Thread.sleep(random.nextInt(3) * 1000 + 1000);
        latch.countDown();
        long t2 = System.currentTimeMillis();
        System.out.println("Stub mission cost " + (t2 -t1));
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        if (acquire)
          semaphore.release();
      }
    }
  }
}
