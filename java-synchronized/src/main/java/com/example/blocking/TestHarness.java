package com.example.blocking;


import java.util.concurrent.CountDownLatch;

public class TestHarness {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch startGet = new CountDownLatch(1);
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task run");
            }
        };
        final CountDownLatch endGate = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        startGet.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        long start = System.nanoTime();
        startGet.countDown();
        endGate.await();
        long end = System.nanoTime();
        System.out.println("end-start = " + (end-start));
    }
}
