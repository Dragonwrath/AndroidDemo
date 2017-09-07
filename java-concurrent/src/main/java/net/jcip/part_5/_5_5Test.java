package net.jcip.part_5;


import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class _5_5Test {
    public static void main(String[] args) throws Exception{
        semaphoreTest();
    }

    private static void futureTaskTest() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "hahhahahaha";
            }
        });

        Thread thread = new Thread(task);

        long t1 = System.nanoTime();
        thread.start();
        System.out.println();
        String s = task.get();
        long t2 =System.nanoTime();
        System.out.println("s = " + s);
        System.out.println("(t2-t1) = " + (t2 - t1));

    }

    private static void countdownTest() {
        CountDownRunnable runnable = new CountDownRunnable();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static void semaphoreTest() {
        final Semaphore semaphore = new Semaphore(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
//                    semaphore.release();
                    throw new RuntimeException();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

}

class CountDownRunnable implements Runnable {
    final static CountDownLatch lock = new CountDownLatch(4);

    @Override
    public void run() {
        lock.countDown();
        while (lock.getCount() != 0) {
            try {
                lock.await();
                System.out.println(Thread.currentThread().getName() + "-----Start");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
