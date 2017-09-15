package net.jcip.part_15.item_3;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockPauseRandom  {
    private final Lock lock = new ReentrantLock(false);
    private int seed;

    public ReetrantLockPauseRandom(int seed) {
        this.seed = seed;
    }

    public int nextInt(int n) {
        lock.lock();
        return n;
    }
}
