package net.jcip.part_13.item_5;


import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteMap<K, V> {
  private final Map<K, V> map;
  private final ReadWriteLock lock = new ReentrantReadWriteLock();
  private final Lock r = lock.readLock();
  private final Lock w = lock.writeLock();

  public ReadWriteMap(Map<K, V> map) {
    this.map = map;
  }

  public V put(K key, V value) {
    w.lock();
    try {
      return map.put(key, value);
    } finally {
      w.unlock();
    }
  }
  //对于写入方法执行相同操作

  public V get(Object key) {
    r.lock();
    try {
      return map.get(key);
    } finally {
      r.unlock();
    }
  }//对于只读的Map方法执行相同的操作
}
