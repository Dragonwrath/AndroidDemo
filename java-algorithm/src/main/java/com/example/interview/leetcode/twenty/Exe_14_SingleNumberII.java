package com.example.interview.leetcode.twenty;

public class Exe_14_SingleNumberII{
  public static void main(String[] args){

  }

  static class Solution{
    public int singleNumber(int[] A){
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

  /**
   * Given an array of integers, every element appears twice except for one.
   * Find that single one.
   * Note:Your algorithm should have a linear runtime complexity.
   * Could you implement it without using extra memory?
   *
   * @param A array of input
   * @return the single num in array
   */
  public int singleNumberII(int[] A){
    if(A.length==1) return A[0];
    int sum=A[0];
    for(int i=1;i<A.length;i++) {
      sum=sum^A[i];
    }
    return sum;
  }

  public int singleNumberIII(int[] A){
    if(A.length==1) return A[0];
    int oneTime=0;
    int twoTime=0;
    int threeTime=0;
    for(int i=0;i<A.length;i++) {
      twoTime|=oneTime&A[i];
      oneTime^=A[i];
      threeTime=oneTime&twoTime;
      oneTime&=~threeTime;
      twoTime&=~threeTime;
    }
    return oneTime;
  }

}
