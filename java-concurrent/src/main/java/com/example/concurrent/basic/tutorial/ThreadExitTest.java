package com.example.concurrent.basic.tutorial;

public class ThreadExitTest {

  public static void main(String[] args) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        super.run();
        System.out.println("Hook is finished");
      }
    });

    System.out.println("Mission is finished");
  }
}
