package com.example.interview.leetcode.twenty;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i isgas[i].
 * You have a car with an unlimited gas tank and it costscost[i]of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note:  The solution is guaranteed to be unique.
 */
public class Exe_16_GasStation{
  public static void main(String[] args){

  }

  static class Solution{
    public int canCompleteCircuit(int[] gas,int[] cost){
      if(gas==null) return -1;
      if(cost==null) return gas[0];
      int sum=0;
      out:
      for(int i=0;i<gas.length;) {
        for(int j=i;j<gas.length+i;j++) {
          int cache;
          if(j>=gas.length){
            cache=j-gas.length;
          }else cache=j;
          sum=gas[cache]+sum-cost[cache];
          if(sum<0){
            i++;
            sum=0;
            continue out;
          }
        }
        return i;
      }
      return -1;
    }
  }

  /**
   * 方法思路，如果从i点出发，可以跑完全程，
   * 则从i-1点出发无法跑完全程，找出i-1点
   */
  static class BetterSolution{

    public int canCompleteCircuit(int[] gas,int[] cost){
      if(gas==null) return -1;
      if(cost==null) return 0;
      int remain=0, total=0, index=-1;
      for(int i=0;i<gas.length;i++) {
        total+=gas[i]-cost[i];
        remain+=gas[i]-cost[i];
        if(remain<0){
          remain=0;
          index=i;
        }
      }
      return total>=0?index+1:-1;
    }
  }
}
