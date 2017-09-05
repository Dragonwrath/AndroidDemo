package com.example;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.SynchronousQueue;

public class Test {
    static Vector<String> list = new Vector<>(9);
  static  CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>(new String[9] );

    public static void main(String[] args) throws InterruptedException {

        int size = list.size();
        Thread thread = new ListThread(list);
        ListThread secondThread = new ListThread(list);
        ListThread thirdThread = new ListThread(list);
        thread.start();
        secondThread.start();
        thirdThread.start();
        Thread.sleep(1000);
        for (String s : list) {
            System.out.println("s = " + s);
        }
        SynchronousQueue queue= new SynchronousQueue();

    }
    static class ListThread extends Thread {
        Vector<String> list;

        public ListThread(Vector<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "--start");
            list.set(0,"1--"+Thread.currentThread().getName());
            list.set(1,"2--"+Thread.currentThread().getName());
            list.set(2,"3--"+Thread.currentThread().getName());
        }
    }
}
