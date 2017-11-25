package com.example.interview.leetcode.twenty;

import com.example.interview.leetcode.ListNode;

public class Exe_9_LinkedListCycle{

  public static void main(String[] args){
    ListNode head=new ListNode(0);
    SlowSolution slowSolution=new SlowSolution();
    ListNode cycle=slowSolution.detectCycle(head);
    System.out.println(cycle);
    ListNode anchor = head;
    for(int i=1;i<5;i++) {
      anchor.next=new ListNode(i);
      anchor = anchor.next;
    }
    cycle = slowSolution.detectCycle(head);
    System.out.println(cycle);
    anchor.next = head;
    System.out.println(slowSolution.detectCycle(head).val);
  }
  private static class SlowSolution{
    public ListNode detectCycle(ListNode head) {
      if(head == null || head.next == null) {
        return null;
      }
      ListNode tail = head.next;
      ListNode repeatHead;
      while(tail != null) {
        repeatHead = head;
        while(repeatHead != tail){
          if(repeatHead!=tail.next){
            repeatHead=repeatHead.next;
          }else{
            return tail;
          }
        }
        tail = tail.next;
      }
      return null;
    }
  }

  static class Solution {
    public ListNode detectCycle(ListNode head) {
      ListNode anchor = hasCycle(head);
      if(anchor == null){
        return null;
      }
      while(anchor != head) {
        head = head.next;
        anchor = anchor.next;
      }
      return anchor;
    }
    public ListNode hasCycle(ListNode head) {
      if(head == null || head.next == null) return null;
      ListNode slow = head, fast = head;
      while(fast!= null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) {
          return slow;
        }
      }
      return null;
    }
  }

}
