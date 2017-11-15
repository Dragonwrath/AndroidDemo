package com.example.concurrent.atresia;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CycleBarrierTest {

  public static void main(String[] args) {
    doWithRunnable();
  }

  private static void doWithRunnable() {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(8, new Runnable() {
      @Override
      public void run() {
        System.out.println("All finished");
      }
    });
    ExecutorService service = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      service.submit(new BarrierRunnable(cyclicBarrier));
    }
  }

  private static class BarrierRunnable implements Runnable {

    private final CyclicBarrier barrier;
    private static int sleep ;
    private static int count ;

    private BarrierRunnable(CyclicBarrier barrier) {
      this.barrier = barrier;
    }

    @Override
    public void run() {
      //do some work
      try {
        sleep = (++count / 8) * 1000;
        Thread.sleep(sleep);
        barrier.await();
        System.out.println("Mission finished");
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      } finally {

      }
    }
  }

}
