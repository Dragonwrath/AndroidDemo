package com.example.interview.leetcode;

public class Exe_15_Candy{
  public static void main(String[] args){
    Solution solution=new Solution();
    int[] ratings = {1,3,4,3,2,1};
    int candy=solution.candy(ratings);
    System.out.println(candy);
  }

  static class Solution{
    public int candy(int[] ratings){
      if(ratings==null||ratings.length==0) return 0;
      if(ratings.length==1) return 1;
      int[] cache=new int[ratings.length];
      for(int i=0;i<cache.length;i++) {
        cache[i]=1;
      }
      for(int i=1;i<ratings.length;i++) {
        if(ratings[i]>ratings[i-1]){
          cache[i]= cache[i-1] + 1;
        }else if(ratings[i]<ratings[i-1]){
          if(cache[i-1]==1){
            for(int j=i-1;j>=0;j--) {
              if(ratings[j+1]<ratings[j] && cache[j+1]>=cache[j])
                cache[j]= cache[j] + 1;
              else break;
            }
          }
        }
      }
      int sum=0;
      for(int i : cache) {
        sum+=i;
      }
      return sum;
    }
  }
}
