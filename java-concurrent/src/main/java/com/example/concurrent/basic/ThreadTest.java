package com.example.concurrent.basic;

import java.util.Date;

public class ThreadTest {
  public static void main(String[] args) throws Exception{
    final Thread thread = new Thread(new PrintRunnable());
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
          System.out.println("sleep finished----" + new Date());
//          thread.join();
          Thread.sleep(1000);
          System.out.println("second sleep finished--" + new Date());

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    thread.start();

    System.out.println("main finished");

  }

  private static class PrintRunnable implements Runnable {

    @Override
    public void run() {
      try {
        Thread.sleep(5000);
        System.out.println("join sleep finished--" +  new Date());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
