package com.example.interview.leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class Exe_7_BinaryTreePreorderTraversal{

  public static void main(String[] args){
    Solution solution=new Solution();
    ArrayList<Integer> list=solution.preorderTraversal(new TreeNode(1));
    System.out.println("list = "+list);
  }

  private static class Solution {
    private final ArrayList<Integer> list = new ArrayList<>();
    private ArrayList<Integer> recursiveTraversal(TreeNode root) {
      if(root == null) return list;
      list.add(root.val);
      if(root.left != null) recursiveTraversal(root.left);
      if(root.right != null) recursiveTraversal(root.right);
      return list;
    }

    private final Stack<TreeNode> cache = new Stack<>();
    private ArrayList<Integer> preorderTraversal(TreeNode root) {
      if(root == null) return list;
      while(root != null) {
        list.add(root.val);
        if(root.right != null) {
          cache.push(root.right);
        }
        if(root.left != null) {
          cache.push(root.left);
        }
        if(!cache.empty())
          root = cache.pop();
        else {
          root = null;
        }
      }
      return list;
    }
  }
}
