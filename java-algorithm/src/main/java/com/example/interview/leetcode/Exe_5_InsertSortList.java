package com.example.interview.leetcode;

import java.util.Random;


/**
 * Sort a linked list using insertion sort.
 */
public class Exe_5_InsertSortList{

  public static void main(String[] args){
    Solution solution=new Solution();
    ListNode node=new ListNode(5);
    ListNode prev =node;
    Random random=new Random();
    for(int i=0;i<10;i++) {
      ListNode listNode=new ListNode(random.nextInt(100));
      prev.next = listNode;
      prev = listNode;
    }
    long t1=System.nanoTime();
    solution.insertionSortList(node);
    System.out.println(System.nanoTime() - t1);
  }

  public static class Solution {
    public ListNode insertionSortList(ListNode head) {
      if(head == null || head.next == null) return head;
      ListNode tail = head;
      ListNode node = head.next;
      ListNode headCopy;
      while(node!= null) {
        if(node.val <= head.val) { //如果小于头部，则直接插入为head
          tail.next = node.next;
          node.next = head;
          head = node;
          node = tail.next;
        } else { //依次比较到tail节点位置的位置
          if(node.val >= tail.val) { //减少比较次数
            tail = node;
            node = node.next;
          } else{
            headCopy = head;
            while(headCopy.next != node){
              if(headCopy.next.val >= node.val) {
                tail.next = node.next;
                node.next = headCopy.next;
                headCopy.next = node;
                node = tail.next;
                break;
              } else {
                headCopy = headCopy.next;
              }
            }
          }
        }
      }
      return head;
    }
  }


  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x){
      val=x;
      next=null;
    }
  }
}
