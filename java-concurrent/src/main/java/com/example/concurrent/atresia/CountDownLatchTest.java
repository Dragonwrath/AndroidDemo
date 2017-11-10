package com.example.concurrent.atresia;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class CountDownLatchTest {
  public static void main(String[] args) {
    doWithCallable();
  }

  //step-1 use run runnable stub instance
  private static void doWithRunnable() {
    int count = 10;
    CountDownLatch start = new CountDownLatch(1);
    CountDownLatch end = new CountDownLatch(count);
    CountDownRunnable runnable = new CountDownRunnable(start, end);
    Executor service = Executors.newCachedThreadPool();
    for (int i = 0; i < count; i++) {
      service.execute(new CountDownRunnable(start, end));
    }

    System.out.println("Mission start at " + new Date());
//    start.countDown();

    try {
      end.await();
      System.out.println("Mission end at " + new Date());
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println("Mission has error " + e);
    }
  }

  //step-2 use callable for check state
  private static void doWithCallable() {
    int count = 10;
    CountDownLatch endLatch = new CountDownLatch(count);
    ExecutorService service = Executors.newCachedThreadPool();
    ExecutorService cachePool = Executors.newCachedThreadPool();
    BlockingQueue<ConsumerRunnable.Result> consumerQueue = new LinkedBlockingQueue<>();
    long t1 = System.currentTimeMillis();
    for (int i = 0; i < count; i++) {
      if (service.isTerminated()) {
        Future<String> future = service.submit(new CountDownCallable(endLatch));
        cachePool.submit(new ConsumerRunnable<>(future,consumerQueue, endLatch));
      }
    }
    try {
      endLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(String.format(Locale.getDefault(),
        "%s cost %d seconds", "Mission ", System.currentTimeMillis() - t1));

    int failed = 0;
    for (ConsumerRunnable.Result result : consumerQueue) {
      if (result.failed) {
        failed ++;
      } else {
        System.out.println(result.getResult());
      }
    }
    System.out.println("failed = " + failed);
  }

  private static void doWithFutureTask() {
    int count = 10;
    CountDownLatch endLatch = new CountDownLatch(10);
    ExecutorService service = Executors.newCachedThreadPool();
    for (int i = 0; i < count; i++) {
      Future<String> future = service.submit(new CountDownCallable(endLatch));
      try {
        long t1 = System.currentTimeMillis();
        String s = future.get();
        long t2 = System.currentTimeMillis();
        String format = String.format(Locale.getDefault(), "Mission %d cost %d seconds", i, t2 - t1);
        System.out.println(format);
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
  }

  private static class CountDownRunnable implements Runnable {
    private final CountDownLatch startLatch;
    private final CountDownLatch endLatch;

    private CountDownRunnable(CountDownLatch startLatch, CountDownLatch endLatch) {
      this.startLatch = startLatch;
      this.endLatch = endLatch;
    }

    @Override
    public void run() {
      Random random = new Random();
      try {
//        startLatch.await();
        System.out.println(Thread.currentThread().getName() + " start " );
        int millis = random.nextInt(10) * 1000;
        Thread.sleep(millis);
        endLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " end " );
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private static class CountDownCallable implements Callable<String> {
    private CountDownLatch endLatch;
    private final Random random = new Random();

    private CountDownCallable(CountDownLatch endLatch) {
      this.endLatch = endLatch;
    }

    @Override
    public String call() throws Exception {
      long t1 = System.currentTimeMillis();
      Thread.sleep(random.nextInt(5) * 1000  );
//      endLatch.countDown();
      long t2 = System.currentTimeMillis();
      return String.format(Locale.getDefault(), "%15s cost %d seconds", Thread.currentThread().getName(), t2 - t1);
    }
  }

  private static class ConsumerRunnable<T> implements Runnable {

    private final Future<T> future;
    private final BlockingQueue<Result> resultQueue;
    private final CountDownLatch endLatch;

    private ConsumerRunnable(Future<T> future, BlockingQueue<Result> resultQueue, CountDownLatch endLatch) {
      this.future = future;
      this.resultQueue = resultQueue;
      this.endLatch = endLatch;
    }

    @Override
    public void run() {
      try {
        T t = future.get(3, TimeUnit.SECONDS);
        Result<T> result = new Result<>(false, t);
        resultQueue.add(result);
      } catch (Exception e) {
        String s = e.getMessage();
        Result<String> result = new Result<>(true, s);
        resultQueue.add(result);
      } finally {
        endLatch.countDown();
      }
    }

    class Result<T> {
      final T result;
      final boolean failed;

      Result(boolean failed, T result) {
        this.failed = failed;
        this.result = result;
      }

      T getResult() {
        return result;
      }

      public boolean isFailed() {
        return failed;
      }
    }
  }
}

