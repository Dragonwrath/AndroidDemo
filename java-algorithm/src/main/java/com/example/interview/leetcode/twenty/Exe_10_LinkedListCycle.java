package com.example.interview.leetcode.twenty;

import com.example.interview.leetcode.ListNode;

public class Exe_10_LinkedListCycle{
  public class Solution {
    public boolean hasCycle(ListNode head) {
      if(head == null || head.next == null) return true;
      ListNode slow = head, fast = head;
      while(fast!= null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) {
          return false;
        }
      }
      return true;
    }
  }
}
