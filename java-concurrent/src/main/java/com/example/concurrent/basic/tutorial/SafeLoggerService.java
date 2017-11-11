package com.example.concurrent.basic.tutorial;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class SafeLoggerService {
  private final BlockingQueue<String> queue;
  private final LoggerThread loggerThread;
  private final PrintWriter writer;
  private boolean ishutdown;
  private int reservations;

  public SafeLoggerService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
    this.queue = queue;
    this.loggerThread = loggerThread;
    this.writer = writer;
  }

  public void start() {
    loggerThread.start();
  }

  public void stop() {
    synchronized (this) {
      ishutdown = true;
    }
    loggerThread.interrupt();
  }

  public void log(String msg) throws InterruptedException {
    synchronized (this) {
      if (ishutdown) throw  new IllegalStateException();
      ++reservations;
    }
    queue.put(msg);
  }

  private class LoggerThread extends Thread {
    @Override
    public void run() {
      try {
        while (true) {
          try {
            synchronized (SafeLoggerService.this) {
              if (ishutdown && reservations == 0) {
                break;
              }
              String msg = queue.take();
              synchronized (SafeLoggerService.this) {
                --reservations;
              }
              writer.println(msg);
            }
          } catch (InterruptedException e) { //noinspections }
          }
        }
      } finally{
        writer.close();
      }
    }
  }

}
