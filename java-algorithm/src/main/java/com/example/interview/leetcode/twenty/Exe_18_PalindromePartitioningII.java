package com.example.interview.leetcode.twenty;

public class Exe_18_PalindromePartitioningII {
  static class Solution {
    public int minCut(String s) {
      if (s == null || s.length() == 0) return 0;
      int num = 0;
      boolean[][] boxes = new boolean[s.length()][s.length()];
      num = loopSearch(boxes, s.length());
      return num==0 ? 0 :(num -1);
    }

    public int loopSearch(boolean[][] boxes,int right){
      int result = 0;
      for (int i = 0; i < boxes.length; i++) {
        if (i ==0 && boxes[i][right-1]) {
          return result+1;
        }
        if (boxes[i][right-1]) {
          result = loopSearch(boxes, right-i);
        }
      }
      return result;
    }
  }
}
