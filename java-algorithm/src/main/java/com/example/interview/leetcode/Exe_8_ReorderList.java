package com.example.interview.leetcode;

import com.example.NotPassed;

@NotPassed
public class Exe_8_ReorderList{
  public static void main(String[] args){
    ListNode head=new ListNode(0);
    ListNode headCopy=head;
    for(int i=1;i<4;i++) {
      headCopy.next=new ListNode(i);
      headCopy=headCopy.next;
    }
    Solution solution=new Solution();
    solution.reorderList(head);
    while(head!=null){
      System.out.println(head.val);
      head=head.next;
    }
  }

  private static class Solution{
    void reorderList(ListNode head){
      if(head==null||head.next==null) return;
      ListNode tail=slowAndFast(head);
      tail=reverseListNode(tail);
      mergeListNode(head,tail);
    }

    private void mergeListNode(ListNode head,ListNode tail){
      ListNode cacheHead;
      ListNode cacheTail;
      while(head!=null&&tail!=null){
        cacheHead=head.next;
        cacheTail=tail.next;
        head.next=tail;
        tail.next=cacheHead;
        tail=cacheTail;
        head=cacheHead;
      }
    }

    private ListNode reverseListNode(ListNode tail){
      ListNode cacheHead;
      ListNode cacheTail=tail.next;
      tail.next=null;
      while(cacheTail!=null){ //反转指向
        cacheHead=cacheTail;
        cacheTail=cacheTail.next;
        cacheHead.next=tail;
        tail=cacheHead;
      }
      return tail;
    }

    private ListNode slowAndFast(ListNode head){
      ListNode mid=head, tail=head.next;
      while(tail!=null&&tail.next!=null){
        mid=mid.next;
        tail=tail.next.next;
      }
      tail=mid.next;
      mid.next=null; //切断head的联系
      return tail;
    }

  }
}
