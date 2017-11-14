package com.example.interview;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 非递归打印,
 * 1、通过Fork-join框架，将任务分解为递归任务
 * 2、通过对象递归
 */

class NonRecursivePrint {
  int i;

  private NonRecursivePrint(int i) {
    this.i = i;
  }

  void print() {
    if (i > 100)
      return;
    System.out.println("i = " + i);
    new NonRecursivePrint(i + 1).print();
  }

  private static class ForkJoinPrint {

    public static void main(String[] args) {
      ForkJoinPool pool = new ForkJoinPool();
      pool.invoke(new Ex(0));
    }

    static class Ex extends RecursiveAction {
      int i;

      Ex(int i) {
        this.i = i;
      }

      @Override
      protected void compute() {
        if (i > 100)
          return;
        System.out.println("i = " + i);
        invokeAll(new Ex(i + 1));
      }
    }
  }
}
