package com.example.interview.leetcode.twenty;

import java.util.ArrayList;
import java.util.HashMap;

public class Exe_17_CloneGraph {
  static class Solution{
    private HashMap<Integer,UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
      return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
      if(node == null) return null;
      if(map.containsKey(node.label)){
        return map.get(node.label);
      }
      UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
      map.put(clone.label,clone);
      for(UndirectedGraphNode neighbor:node.neighbors){
        clone.neighbors.add(clone(neighbor));
      }
      return clone;
    }
  }

  static class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<>(); }
  }
}
