package com.example.interview.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
*思路CSDN Allan:*
*    =================================思路Allan========================================*
*    s ="catsand",*
*    dict =["cat", "cats", "and", "sand", "dog"].*
*
*    动态规划根本思想是记录状态值:*
*    DP[i][j]:*
*    j    0       1       2       3       4       5       6*
*    i*
*    0       c       ca      cat(1)  cats(1) catsa   catsan  catsand*
*    1               a       at      ats     atsa    atsan   atsand*
*    2                       t       ts      tsa     tsan    tsand*
*    3                               s       sa      san     sand(1)*
*    4                                       a       an      and(1)*
*    5                                               n       nd*
*    6                                                       d*
*
*    DP[i][j]里:*
*    0       c       ca      cat(1)  cats(1) catsa   catsan  catsand*
*    1       a       at      ats     atsa    atsan   atsand*
*    2       t       ts      tsa     tsan    tsand*
*    3       s       sa      san     sand(1)*
*    4       a       an      and(1)*
*    5       n       nd*
*    6       d*
*    思路:*
*    DP[i][j]存放着字符串s的所有子字符串在dict中的状态值。*
*    遍历顺序是先搜索i到串尾的子串，若子串在dict里，再搜索串头到i的子串。*
*    c a t s a n d*
*    j     i*
*    比如，dp[3][3]=1表明"sand"在dict里，再搜索cat......*
*    再搜索顺序为cat at t......*
*
*
*    output(6,s):*
*    i=6     k:0 1 2 3 4 5 6*
*    dp[k][i-k]:偏移为k，截断字符串长度i-k+1*
*    沿着次对角线遍历,相当于从头部每隔一个字符截断!!!*
*    dp[0][6]:偏移为0，截断字符串长度7      0*
*    dp[1][5]:偏移为1，截断字符串长度6      0*
*    dp[2][4]:偏移为2，截断字符串长度5      0*
*    dp[3][3]:偏移为3，截断字符串长度4      1   -->output(2,s)*
*    dp[4][2]:偏移为4，截断字符串长度3      1   -->output(3,s)*
*    dp[5][1]:偏移为5，截断字符串长度2      0*
*    dp[6][0]:偏移为6，截断字符串长度1      0*
*
*    output(2,s):*
*    i=2     k:0 1 2*
*    dp[k][i-k]:偏移为k，截断字符串长度i-k+1*
*    沿着次对角线遍历,相当于从头部每隔一个字符截断!!!*
*    dp[0][2]:偏移为0，截断字符串长度3      1   -->output(-1,s)*
*    dp[1][1]:偏移为1，截断字符串长度2      0*
*    dp[2][0]:偏移为2，截断字符串长度1      0*
*
*    output(-1,s):*
*    ......*
*
*
*    mystring.push_back(s.substr(k,i-k+1));*
*    output(k-1,s);*
*    s.substr(k,i-k+1)==>递归output(k-1,s)!!!*
*    偏移为k,截断长度i-(k-1);                           ------*
*    ==>递归为k-1                                              |--->处理字符串长度i*
*    从偏移为0，截断长度k开始以次对角线方向遍历!!!       ------*
*    ================================================================================*
*
*/
public class Exe_11_WordBreakII{

  public static void main(String[] args){
    long x=Runtime.getRuntime().freeMemory();
//    String[] strings={"cat","cats","and","sand","dog"};
//    String original = "catsanddog";
    String[] strings={"aaaa","aaa"};
    String original = "aaaaaaa";
    HashSet<String> set=new HashSet<>();
    set.addAll(Arrays.asList(strings));
//    OutOfMemorySolution solution = new OutOfMemorySolution();
//    ArrayList<String> list = solution.wordBreak(original, set);
//    OutOfMemorySolutionII solution=new OutOfMemorySolutionII();
//    ArrayList<String> list=solution.wordBreak(original,set);
    Solution solution=new Solution();
    ArrayList<String> list=solution.wordBreak(original,set);
    System.out.println(list.toString());
  }

  private static class OutOfMemorySolution{
    ArrayList<String> wordBreak(String s,Set<String> dict){
      StringTask task=new StringTask(s,dict);
      return task.compute();
    }

    static class StringTask{
      private final String string;
      private final Set<String> dict;

      private StringTask(String string,Set<String> dict){
        this.string=string;
        this.dict=dict;
      }

      private ArrayList<String> compute(){
        ArrayList<String> list=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        for(String s : dict) {
          if(string.startsWith(s)){
            String nextString=string.substring(s.length(),string.length());
            if(!nextString.equals(""))
              map.put(s,nextString);
            else{
              list.add(s);
              return list;
            }
          }
        }
        if(map.size()>0){
          for(String key : map.keySet()) {
            StringTask task=new StringTask(map.get(key),dict);
            List<String> result=task.compute();
            for(String secondString : result) {
              list.add(key+" "+secondString);
            }
          }
        }
        return list;
      }
    }
  }

  private static class OutOfMemorySolutionII{
    private ArrayList<String> wordBreak(String string,Set<String> dict){
      ArrayList<String> list=new ArrayList<>();
      if(string==null||string.equals("")) return list;
      if(dict.size()==0) return list;
      Map<String,String> map=new HashMap<>();
      for(String s : dict) {
        if(string.startsWith(s)){
          String nextString=string.substring(s.length(),string.length());
          if(!nextString.equals(""))
            map.put(s,nextString);
          else{
            list.add(s);
            return list;
          }
        }
      }
      if(map.size()>0){
        for(String key : map.keySet()) {
          List<String> result=wordBreak(map.get(key),dict);
          for(String secondString : result) {
            list.add(key+" "+secondString);
          }
        }
      }
      return list;
    }
  }

  private static class OutOfMemorySolutionIII {

    public ArrayList<String> wordBreak(String s,Set<String> dict){
      ArrayList<String> list=new ArrayList<>();
      if(s==null||s.equals("")) return list;
      if(dict.size()==0) return list;
      list = loopSearch(s, dict);
      return list;
    }

    public ArrayList<String> loopSearch(String s,Set<String> dict) {
      ArrayList<String> result=new ArrayList<>();
      ArrayList<String> cache = new ArrayList<>();
      for(String start : dict) {
        if(s.startsWith(start)){
          String substring=s.substring(start.length(),s.length());
          if(substring.length() > 0){
            if(cache.size()>=0){
              cache=loopSearch(substring,dict);
              for(int i=0, size=cache.size();i<size;i++) {
                cache.set(i,start +" "+cache.get(i));
              }
              result.addAll(cache);
            }
          } else {
            result.add(start);
            return result;
          }
        }
      }
      return result;
    }
  }

  private static class Solution{
    ArrayList<String> list=new ArrayList<>();

    public ArrayList<String> wordBreak(String s,final Set<String> dict){
      if(s==null||s.equals("")) return list;
      if(dict.size()==0) return list;
      boolean[][] boxes = new boolean[s.length()][s.length()];
      for(int i=0;i<boxes.length;i++) {
        for(int j=i;j<boxes[i].length;j++) {
          boxes[i][j] = dict.contains(s.substring(i,j + 1));
        }
      }
      loopSearch(s, boxes, s.length(),"");
      final LinkedList<String> strings=new LinkedList<>();
      for(String s1 : dict) {
        strings.add(s1);
      }
      Collections.sort(list,new Comparator<String>(){
        @Override
        public int compare(String o1,String o2){
          String  s1=o1.split(" ")[0];
          String  s2=o2.split(" ")[0];
          int i=strings.indexOf(s1)-strings.indexOf(s2);
          if(i == 0) {
            return o1.length()-o2.length();
          }
          return i;
        }
      });
      return list;
    }

    private void loopSearch(String s,boolean[][] boxes,int right, String result){
      for(int i=0;i<right;i++) {
        if(i==0 && boxes[i][right-1]) {
          String string = s.substring(0, right) +" " + result;
          list.add(string.trim());
        }
        if(boxes[i][right-1]){
          loopSearch(s,boxes,i,s.substring(i, right) + " " + result);
        }
      }
    }
  }
}
