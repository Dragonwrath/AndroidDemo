package com.example.interview.leetcode;

public class Exe_14_SingleNumberII{
  public static void main(String[] args){

  }

  static class Solution{
    public int singleNumber(int[] A) {
      if(A.length==1) return 1;
      int cache=Integer.MIN_VALUE;
      int index=-1;
      for(int i=0, size=A.length;i<size;i++) {
        cache=A[i];
        index=i;
        for(int j=0;j<A.length;j++) {
          if(cache==A[j]&&index!=j){
            break;
          }else{
            if(j==A.length-1){
              return cache;
            }
          }
        }
      }
      return cache;
    }
  }
}
