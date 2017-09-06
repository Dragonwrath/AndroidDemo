package com.example.concurrent.threadlocal;


import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;

public class ThreadLocalTest {
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
//        auto();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                int i = ThreadLocalTest.ThreadId.get();
                System.out.println(Thread.currentThread().getName() + "----->" + i);
            }
        };
//        SingleThread t1 = new SingleThread("SingleThread -- 1");
//        SingleThread t2 = new SingleThread("SingleThread -- 2");
//        SingleThread t3 = new SingleThread("SingleThread -- 3");
//        SingleThread t4 = new SingleThread("SingleThread -- 4");
        SingleThread t1 = new SingleThread(run,"SingleThread -- 5");
        SingleThread t2 = new SingleThread(run,"SingleThread -- 6");
        SingleThread t3 = new SingleThread(run,"SingleThread -- 7");
        SingleThread t4 = new SingleThread(run,"SingleThread -- 8");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    static void auto() {
        AtomicInteger atomic = new AtomicInteger();

        int add = atomic.getAndAdd(HASH_INCREMENT);
        System.out.println("add = " + add);
        add = atomic.getAndAdd(HASH_INCREMENT);
        System.out.println("add = " + add);
        for (int i = 0; i < 10; i++) {
            add = atomic.get();
            System.out.println("add = " + add);
        }
    }

    static class ThreadId {
        // Atomic integer containing the next thread ID to be assigned
        private static final AtomicInteger nextId = new AtomicInteger(0);

        // Thread local variable containing each thread's ID
        private static final ThreadLocal<Integer> threadId =
                new ThreadLocal<Integer>() {
                    @Override protected Integer initialValue() {
                        return nextId.getAndIncrement();
                    }
                };

        // Returns the current thread's unique ID, assigning it if necessary
        public static int get() {
            Integer integer = threadId.get();
            System.out.println(Thread.currentThread().getName() + "----->" + integer);
            return integer;
        }
    }


}
class SingleThread extends Thread{

    SingleThread(Runnable run, String name) {
        super(run, name);
    }

    public SingleThread(String s) {
        super(s);
    }

    void get() {
        int i = ThreadLocalTest.ThreadId.get();
        System.out.println(Thread.currentThread().getName() + "----->" + i);
    }
}