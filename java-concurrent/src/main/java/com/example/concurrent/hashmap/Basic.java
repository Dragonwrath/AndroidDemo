package com.example.concurrent.hashmap;

public class Basic {
  class StripedMap {
    private final static int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    private class Node {
      private Node next;
      private Object key;
      private Object value;
    }

    public StripedMap(int numBuckets) {
      this.buckets = new Node[numBuckets];
      this.locks = new Object[numBuckets];
      for (Object lock : locks) {
        lock = new Object();
      }
    }

    private final int hash(Object key) {
      return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
      int hash = hash(key);
      synchronized (locks[hash % buckets.length]) {
        for (Node m = buckets[hash]; m != null; m = m.next) {
          if (m.key.equals(key)) {
            return m.value;
          }
        }
      }
      return null;
    }

    public void clear() {
      for (int i = 0; i < buckets.length; i++) {
        synchronized (locks[i % buckets.length]) {
          buckets[i] = null;
        }
      }
    }
  }
}