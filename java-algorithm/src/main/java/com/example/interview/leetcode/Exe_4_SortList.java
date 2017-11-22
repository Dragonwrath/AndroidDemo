package com.example.interview.leetcode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class Exe_4_SortList{

  static class Solution {
    public ListNode sortList(ListNode head) {
      if(head == null || head.next == null) return head;
      ListNode mid = head;
      ListNode end = head.next;
      while(end != null && end.next != null) {
        mid = mid.next;
        end = end.next.next;
      }
      ListNode right = sortList(mid.next);
      mid.next = null;
      ListNode left=sortList(head);
      ListNode newHead = new ListNode(0);
      ListNode newHeadCopy = newHead;
      while(left != null && right != null) {
        if(left.val < right.val) {
          newHeadCopy.next = left;
          left = left.next;
        } else {
          newHeadCopy.next = right;
          right = right.next;
        }
        newHeadCopy = newHeadCopy.next;
      }
      if(left != null) {
        newHeadCopy.next = left;
      }
      if(right != null) {
        newHeadCopy.next = right;
      }
      return newHead.next;
    }

    class ListNode {
      int val;
      ListNode next;

      ListNode(int x){
        val=x;
        next=null;
      }
    }
  }
}
