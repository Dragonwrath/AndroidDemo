package net.jcip.part_8.sectiong_3.item_4;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryTest {
    public static void main(String[] args) {

    }

    private static void factoryTest() {
    }

    private static void privilegedTest() {
        final ExecutorService service = Executors.newCachedThreadPool();
        final ThreadFactory factory = Executors.privilegedThreadFactory();
    }
}
