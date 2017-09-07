package net.jcip.part_5;


import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable {
    public final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] files = root.listFiles(fileFilter);
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    crawl(file);
                } else if(!alreadyIndex(file))  {
                    fileQueue.put(file);
                }
            }
        }
    }

    private boolean alreadyIndex(File file) {
        return fileQueue.contains(file);
    }
}

class Index implements Runnable {

    private volatile boolean started = true;

    public final BlockingQueue<File> fileQueue;


    public Index(BlockingQueue<File> fileQueue) {
        this.fileQueue = fileQueue;
    }

    @Override
    public void run() {
        start();
        try {
            while (started) {
                indexFile(fileQueue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file)  {
        System.out.println(file.getAbsolutePath());
    }

    private void start() {
        started = true;
    }

    private void quit() {
        started =false;
    }
}