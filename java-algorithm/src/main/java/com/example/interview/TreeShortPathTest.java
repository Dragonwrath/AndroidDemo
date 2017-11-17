package com.example.interview;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 */
class TreeShortPathTest {

  public static void main(String[] args) {
    Node node = new Node(-1, null);
    print(5, node);
  }

  static void print(int n, Node node) {
    final int decrease = n -1;
    for (int i = 0; i < 10; i++) {
      if (n == 0) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(0,node.value);
        Node head = node.prev;
        while (head != null) {
          if (head.value != -1)
            list.add(0, head.value);
          head = head.prev;
        }
        System.out.println(Arrays.toString(list.toArray()));
      } else {
        print(decrease, new Node(i, node));
      }
    }
  }

  static class Node {
    private final int value;
    private final Node prev;

    public Node(int value, Node prev) {
      this.value = value;
      this.prev = prev;
    }
  }
}
