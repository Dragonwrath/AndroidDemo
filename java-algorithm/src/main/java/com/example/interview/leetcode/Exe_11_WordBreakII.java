package com.example.interview.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exe_11_WordBreakII {

  public static void main(String[] args) {
    long x = Runtime.getRuntime().freeMemory();
    OutOfMemorySolution solution = new OutOfMemorySolution();
    String[] strings = {"cat", "cats", "and", "sand", "dog"};
    HashSet<String> set = new HashSet<>();
    set.addAll(Arrays.asList(strings));
    ArrayList<String> list = solution.wordBreak("catsanddog", set);
    System.out.println(list.toString());
    System.out.println(Runtime.getRuntime().freeMemory() - x);

  }

  static class OutOfMemorySolution {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
      StringTask task = new StringTask(s, dict);
      return task.compute();
    }

    static class StringTask {
      private final String string;
      private final Set<String> dict;

      private StringTask(String string, Set<String> dict) {
        this.string = string;
        this.dict = dict;
      }

      private ArrayList<String> compute() {
        ArrayList<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (String s : dict) {
          if (string.startsWith(s)) {
            String nextString = string.substring(s.length(), string.length());
            if (!nextString.equals(""))
              map.put(s, nextString);
            else {
              list.add(s);
              return list;
            }
          }
        }
        if (map.size() > 0) {
          for (String key : map.keySet()) {
            StringTask task = new StringTask(map.get(key), dict);
            List<String> result = task.compute();
            for (String secondString : result) {
              list.add(key + " " + secondString);
            }
          }
        }
        return list;
      }
    }
  }

  static class Solution {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
      return null;
    }
  }
}
