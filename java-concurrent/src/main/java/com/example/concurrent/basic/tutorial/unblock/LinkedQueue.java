package com.example.concurrent.basic.tutorial.unblock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

class LinkedQueue<E> {
  private static class Node<E> {
    final E item;
    final AtomicReference<Node<E>> next;

    Node(E item, Node<E> next) {
      this.item = item;
      this.next = new AtomicReference<>(next);
    }
  }

  //初始化哨兵节点（Sentinel）或者哑节点（Dummy）
  private final Node<E> dummy = new Node<>(null, null);
  private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
  private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

  public boolean put(E item) {
    Node<E> newNode = new Node<>(item, null);
    while (true) {
      Node<E> curTail = tail.get();
      Node<E> tailNext = curTail.next.get();

      if (curTail == tail.get()) {
        if (tailNext != null) {
          //队列处于中间状态，推进尾节点
          //将tail指针推后，再循环，直到找到真正的尾节点
          tail.compareAndSet(curTail, tailNext);
        } else {
          //处于稳定状态，尝试插入新节点
          if(curTail.next.compareAndSet(null, newNode)) {
            //插入操作成功，尝试推进尾节点
            tail.compareAndSet(curTail, newNode);
            return true;
          }
        }
      }
    }
  }
}

class ConcurrentLinedQueue {
  private class Node<E> {
    private final E item;
    private volatile Node<E> next;

    public Node(E item) {
      this.item = item;
    }
  }

  private static AtomicReferenceFieldUpdater<Node, Node> nextUpdater
      = AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "next");
}
