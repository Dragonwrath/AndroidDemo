package com.example.interview.leetcode.twenty;

import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 */
public class Exe_1_ShortTreePath{
  public static void main(String[] args){
    Random random=new Random();
    TreeSet<TreeNode> set=new TreeSet<>();
    TreeNode parent=new TreeNode(31);
    set.add(parent);
    for(int i=1;i<10;i++) {
      int num=random.nextInt(i*31);
      TreeNode node=new TreeNode(num);
      parent.addChild(node);
      set.add(node);
    }
    int path=Source.calculatePath(parent);
    System.out.println("path is "+path);

    for(TreeNode treeNode : set) {
      System.out.println(treeNode.value);
    }

    parent.print();
  }

  static class Source{
    static int calculatePath(TreeNode root){
      if(root==null) return 0;
      if(root.left==null) return calculatePath(root.right)+1;
      if(root.right==null) return calculatePath(root.left)+1;
      return Math.min(calculatePath(root.left),calculatePath(root.right))+1;
    }
  }

  static class TreeNode implements Comparable<TreeNode>{
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value){
      this.value=value;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(TreeNode node){
      Objects.requireNonNull(node);
      return value-node.value;
    }

    void addChild(TreeNode node){
      int i=compareTo(node);
      if(i>0){
        if(left!=null){
          left.addChild(node);
        }else{
          left=node;
        }
      }else if(i<0){
        if(right!=null){
          right.addChild(node);
        }else{
          right=node;
        }
      }else if(i==0){
        if(node.left!=null){
          left.addChild(node.left);
        }
        if(node.right!=null){

        }
      }
    }

    private final StringBuilder builder=new StringBuilder();

    void print(){
      checkNode(builder,this);
      System.out.println(builder.toString());
    }

    void checkNode(StringBuilder builder,TreeNode node){
      if(node.left!=null){
        checkNode(builder,node.left);
      }
      builder.append(node.value+" ");
      if(node.right!=null){
        checkNode(builder,node.right);
      }
    }
  }
}
