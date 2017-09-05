package com.example.blocking;


import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Indexing {
    private static int BOUND  = 10;
    public static void main(String[] args) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);

        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().contains("4_1_1_wireless");
            }
        };

        File diskRoots = new File("D:\\Android\\Training");
        File[] roots = diskRoots.listFiles();
        if (roots != null)
        for (File root : roots) {
            new Thread(new FileCrawler(queue,filter,root)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
