package com.example.interview.leetcode;

import java.util.ArrayList;

public class Exe_6_BinaryTreePostorderTraversal{
  public class Solution {
    private final ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
      if(root == null) return list;
      if(root.left != null) postorderTraversal(root.left);
      if(root.right != null) postorderTraversal(root.right);
      list.add(root.val);
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
