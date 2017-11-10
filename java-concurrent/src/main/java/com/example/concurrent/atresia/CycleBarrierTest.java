package com.example.concurrent.atresia;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CycleBarrierTest {

  public static void main(String[] args) {
  }

  private static void doWithRunnable() {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(8, new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
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

    private BarrierRunnable(CyclicBarrier barrier) {
      this.barrier = barrier;
    }

    @Override
    public void run() {
      //do some work
      try {
        Thread.sleep(1000);
        barrier.await();
        System.out.println("Mission finished");
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      } finally {

      }
    }
  }

}
