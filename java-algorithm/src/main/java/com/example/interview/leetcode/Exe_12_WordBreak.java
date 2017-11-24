package com.example.interview.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Exe_12_WordBreak{
  public static void main(String[] args){
    Solution solution=new Solution();
    HashSet<String> set=new HashSet<>();
    set.add("a");
    set.add("b");
    boolean result=solution.wordBreak("ab",set);
    System.out.println("result = "+result);

  }
  public static class Solution{
    public  boolean wordBreak(String s,final Set<String> dict){
      if(s==null||s.equals("")) return true;
      if(dict.size()==0) return false;
      boolean[][] boxes = new boolean[s.length()][s.length()];
      for(int i=0;i<boxes.length;i++) {
        for(int j=i;j<boxes[i].length;j++) {
          boxes[i][j] = dict.contains(s.substring(i,j + 1));
        }
      }
      boolean result = false;
      return loopSearch(boxes, s.length(), result);
    }


    private boolean loopSearch(boolean[][] boxes,int right,boolean result){
      for(int i=0;i<right;i++) {
        if(i==0 && boxes[i][right-1]) {
          return true;
        }
        if(boxes[i][right-1]){
          result = loopSearch(boxes,i,result);
        }
      }
      return result;
    }
  }
}
