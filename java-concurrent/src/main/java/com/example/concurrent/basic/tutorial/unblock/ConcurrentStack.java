package com.example.concurrent.basic.tutorial.unblock;

import java.util.concurrent.atomic.AtomicReference;

class ConcurrentStack<E> {
  private AtomicReference<Node<E>> top = new AtomicReference<>();

  public void push(E item) {
    Node<E> newHead = new Node<>(item);
    Node<E> oldHead;
    do {
      oldHead = top.get();
      newHead.next = oldHead;
    } while (!top.compareAndSet(oldHead, newHead));
  }

  public E pop() {
    Node<E> newHead;
    Node<E> oldHead;
    do {
      oldHead = top.get();
      if (oldHead == null) {
        return null;
      }
      newHead = top.get().next;
    } while (!top.compareAndSet(oldHead, newHead));
    return oldHead.item;
  }

  private static class Node<E> {
    private final E item;
    private Node<E> next;

    private Node(E item) {
      this.item = item;
    }
  }
}
