package com.example.interview.leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class Exe_7_BinaryTreePreorderTraversal{

  public static void main(String[] args){
    Solution solution=new Solution();
    ArrayList<Integer> list=solution.preorderTraversal(new TreeNode(1));
    System.out.println("list = "+list);
  }

  public static class Solution {
    private final ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> recursiveTraversal(TreeNode root) {
      if(root == null) return list;
      list.add(root.val);
      if(root.left != null) recursiveTraversal(root.left);
      if(root.right != null) recursiveTraversal(root.right);
      return list;
    }

    private final Stack<TreeNode> cache = new Stack<>();
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
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

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}
