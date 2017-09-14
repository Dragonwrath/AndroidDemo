package net.jcip.part_14.item_3;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    private final int BUFFER_SIZE = 10;
    protected final Lock lock = new ReentrantLock();
    private final Condition noFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    @SuppressWarnings("unchecked")
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    private int tail,head,count;

    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                noFull.await();
                if (++tail == items.length) {
                    tail = 0;
                    notEmpty.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();;
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T x = items[head];
            if (++head == items.length) {
                head = 0;
            }
            --count;
            noFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
