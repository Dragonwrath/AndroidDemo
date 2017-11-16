package com.example.concurrent.basic.tutorial;

public class ThreadGate {
  //条件谓词：open-since(n)(isOpen || generation > n)
  private boolean isOpen;
  private int generation;

  public synchronized void close() {
    isOpen = false;
  }

  public synchronized void open() {
    ++generation;
    isOpen = true;
    notifyAll();
  }

  //阻塞知道 opened-since(generation on entry)
  public synchronized void await() throws InterruptedException {
    int arrivalGeneration = generation;
    while (!isOpen && arrivalGeneration == generation) {
      wait();
    }
  }
}
