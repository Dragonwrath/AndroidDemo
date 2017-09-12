package net.jcip.part_12.item_1;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class BoundedBufferTest extends TestCase {

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
}