package com.example.interview.leetcode.twenty;

import com.example.interview.leetcode.RandomListNode;

public class Exe_13_CopyListWithRandomPointer{
  public static void main(String[] args){

  }

  /**
   * A linked list is given such that each node contains an additional random pointer
   * which could point to any node in the list or null.
   * Return a deep copy of the list.
   */
  static class Solution{
    public RandomListNode copyRandomList(RandomListNode head){
      if(head==null) return null;
      if(head.random==null&&head.next==null) return head;
      return head;
    }
  }
}
