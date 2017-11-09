package com.example.concurrent.executor;


import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class SingleExecutorTest {
  public static volatile boolean finish = false;

  public static void main(String[] args) throws Exception {

//        notifyByService();
    callableTest();

  }

  private static void notifyByService() {
    final ExecutorService service = Executors.newScheduledThreadPool(1);
    service.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        finish = true;
//                System.out.println("finish time is " + System.currentTimeMillis());
      }
    });
    printThread();
  }

  private static void printThread() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (!finish)
            System.out.println(System.currentTimeMillis() + "----i----" + i);
          else {
            System.out.println("finish time is " + System.currentTimeMillis());
            break;
          }
        }
      }
    }).start();
  }

  private static void notifyByTimer() {
    final Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        finish = true;
      }
    }, 3000);
    printThread();
  }

  private static void callableTest() throws Exception {
    final Random random = new Random(10);
    ExecutorService service = Executors.newFixedThreadPool(10);
    Future<String> future = null;
    for (int i = 0; i < 10; i++) {
      final int cache = i;
      future = service.submit(new Callable<String>() {
        @Override
        public String call() throws Exception {
          int millis = random.nextInt(10) * 100;
          Thread.sleep(millis);
          return cache + "-----" + new Date();
        }
      });
    }

    String s= future.get();
    System.out.println(s);
    System.out.println(new Date());
  }

  private static void forkJoinTest() {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }
}
