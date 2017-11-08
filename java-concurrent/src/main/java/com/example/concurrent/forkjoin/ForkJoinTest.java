package com.example.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest {


  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();

    Random random = new Random();
    random.setSeed(1000);
    for (int i = 0; i < 1000000; i++) {
      int e = random.nextInt(Integer.MAX_VALUE);
      list.add(e);
    }
    int count = Runtime.getRuntime().availableProcessors();
    ForkJoinPool pool = new ForkJoinPool(count+1);
    Integer[] array = list.toArray(new Integer[1]);
    Solver solver = new Solver(array, 0, array.length);
    long t1 = System.currentTimeMillis();
    pool.invoke(solver);
    int result = solver.getResult();
    long t2 = System.currentTimeMillis();
    System.out.println(result + "-------" +(t2-t1));

    int max = 10004;
    t1 = System.currentTimeMillis();
    for (Integer integer : list) {

      max = Math.min(integer, max);
    }
    t2 = System.currentTimeMillis();
    System.out.println(max + "-------" +(t2-t1));

  }
}

class Solver extends RecursiveAction {

  private static final long serialVersionUID = 5844790428549209736L;

  private int start, end, result;
  private Integer[] array;

  private Solver(Integer[] array, int start, int end) {
    this.start = start;
    this.end = end;
    this.array = array;
  }

  @Override
  protected void compute() {
    if ((end - start) == 1) {
      result = array[start];
    } else {
      int mid = (start + end) / 2;
      Solver front = new Solver(array, start, mid);
      Solver back = new Solver(array, mid, end);
      invokeAll(front, back);
      result = Math.max(front.result, back.result);
    }
  }

  private int getResult() {
    return result;
  }
}
