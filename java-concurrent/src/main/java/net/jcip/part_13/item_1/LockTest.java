package net.jcip.part_13.item_1;


import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

    }

    public boolean sendOnSharedLine(String message) throws InterruptedException{
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    private boolean cancellableSendOnSharedLine(String message) throws InterruptedException {
        return false;
    }
}
