package com.example.concurrent.basic.tutorial;

import java.io.File;
import java.io.FileFilter;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class IndexingService {
  private static final File POISION = new File("");
  private final CrawlerThread producer = new CrawlerThread();
  private final IndexerThread consumer = new IndexerThread();
  private final BlockingQueue<File> queue;
  private final FileFilter fileFilter;
  private final File root;

  public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
    this.queue = queue;
    this.fileFilter = fileFilter;
    this.root = root;
  }

  public void start() {
    producer.start();
    consumer.start();
  }

  public void stop() {
    producer.interrupt();
  }

  public void awaitTermination() throws InterruptedException {
    consumer.join();
  }

  private class CrawlerThread extends Thread {
    @Override
    public void run() {
      try {
        crawl(root);
      } catch (InterruptedException ex) {
        //nothing
      } finally {
        while (true) {
          try {
            queue.put(POISION);
            break;
          } catch (InterruptedException ex) {
            //nothing
          }
        }
      }
    }

    void crawl(File root) throws InterruptedException {
    }
  }

  private class IndexerThread extends Thread {
    @Override
    public void run() {
      try {
        while (true) {
          File file = queue.take();
          if (file == POISION) {
            break;
          } else index(file);

        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    private void index(File file) {
    }
  }

  private class TrackCancelRunnable implements Runnable {
    private final ExecutorService service;
    private final Set<Runnable> set;
    private TrackCancelRunnable(ExecutorService service, Set<Runnable> set) {
      this.service = service;
      this.set = Collections.synchronizedSet(set);
    }

    @Override
    public void run() {
      try {
        Thread.sleep(1000);
        //do work;
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        if (service.isShutdown() && Thread.currentThread().isInterrupted()) {
          set.add(this);
        }
      }
    }
  }
}


