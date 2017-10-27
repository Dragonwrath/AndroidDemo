package net.jcip.part_12.item_1;

import junit.framework.TestCase;

import java.lang.management.ManagementFactory;

public class BoundedBufferTest extends TestCase {

    private static final long LOCKUP_DETECT_TIMEOUT = 1000;

    public void testIsEmptyWhenConstructed() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    public void testIsFullAfterPuts() throws InterruptedException {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    public void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try{
                    int unused = bb.take();
                    fail();
                } catch (InterruptedException success) {}
            }
        };

        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception un) {
            fail();
        }
    }

    public void testPutAndTake() {
        new PutTakeTest(10, 10, 100000).test(); // sample parameters
    }

    public void testLeak() throws InterruptedException{
        final int CAPACITY = 10;
        final BoundedBuffer<Big> bb = new BoundedBuffer<>(CAPACITY);
        long heapSize1 =    ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getCommitted();
        for (int i = 0; i < CAPACITY; i++) {
            bb.put(new Big());
        }
        for (int i = 0; i < CAPACITY; i++) {
            bb.take();
        }
        long heapSize2 =    ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getCommitted();

        assertTrue(Math.abs(heapSize1- heapSize2) > 0);
    }
}