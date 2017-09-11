package net.jcip.part_7.section_2.item_2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogService {
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    public  void start() {}

    public void stop() throws InterruptedException {
    }
}
