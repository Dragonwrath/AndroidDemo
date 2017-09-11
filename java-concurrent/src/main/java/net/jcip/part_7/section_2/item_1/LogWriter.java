package net.jcip.part_7.section_2.item_1;


import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    public LogWriter(BlockingQueue<String> queue, LoggerThread logger) {
        this.queue = queue;
        this.logger = logger;
    }

    public void start() {
        logger.start();
    }

    public void log(String ms) throws InterruptedException {
        queue.put(ms);
    }
    private class LoggerThread extends Thread {
        private PrintWriter writer;

        @Override
        public void run() {
            try {
                while (true) {
                    writer.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }
}
