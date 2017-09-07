package com.example.concurrent.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
//        arrayBlockQueue();
        linkBlockQueue();
    }

    private static void test() {
        String text = "1";
        changetText(text);
        System.out.println("text = " + text);
    }

    private static  void arrayBlockQueue() throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(16);
        for (int i = 0; i < 20; i++) {
            queue.put(String.valueOf(i));
        }
    }

    private static void changetText(String string) {
        string = "2";
    }

    private static void linkBlockQueue() throws InterruptedException {
        int num = 0;
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.put(String.valueOf(i));
        }

        for (String s : queue) {
            System.out.println("queue ["+ "]= " + s);
        }
        System.out.println("For queue.size() = " + queue.size());

        for (int i = 0, size = queue.size(); i < size; i++) {
            System.out.println("queue [" + i + "]= " + queue.take());
        }
        System.out.println("Itr queue.size() = " + queue.size());

    }
}
