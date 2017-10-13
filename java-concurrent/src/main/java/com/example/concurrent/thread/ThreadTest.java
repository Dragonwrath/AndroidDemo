package com.example.concurrent.thread;


public class ThreadTest {
    public static void main(String[] args) {
        final Thread thread = new Thread("1");
        final Thread thread1 = new Thread("1");
        System.out.println("thread1.getName() = " + thread1.getName());
        System.out.println("thread1 = " + thread1);
        System.out.println("thread = " + thread.getName());
        System.out.println("thread = " + thread);

        final boolean equals = thread.equals(thread1);
        System.out.println("equals = " + equals);
    }
}
