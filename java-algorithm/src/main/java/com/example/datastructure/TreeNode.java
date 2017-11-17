package com.example.datastructure;


public class TreeNode<T extends Comparable>{
  private TreeNode<T> parent;
  private TreeNode<T> left;
  private TreeNode<T> right;
  private T value;

  TreeNode(T value){
    this.value=value;
  }

  void setTreeNode(TreeNode<T> other){
    setTreeNode(this,other);
  }


  @SuppressWarnings("unchecked")
  private void setTreeNode(TreeNode<T> source,TreeNode<T> other){
    final int i=other.value.compareTo(source.value);
    if(i>0){
      if(source.getRight()!=null){
        setTreeNode(source.getRight(),other);
      }else{
        source.right=other;
        other.parent=source;
      }
    }else if(i<0){
      if(source.getLeft()!=null){
        setTreeNode(source.getLeft(),other);
      }else{
        source.left=other;
        other.parent=source;
      }
    }
  }

  T getValue(){
    return value;
  }

  private TreeNode<T> getLeft(){
    return left;
  }

  private TreeNode<T> getRight(){
    return right;
  }

  TreeNode<T> getParent(){
    return parent;
  }

  final StringBuilder builder = new StringBuilder();

  void printfTree(TreeNode<T> node){
    StringBuilder builder = new StringBuilder();
    checkNode(builder, node);
    System.out.println(builder.toString());
  }

  private void checkNode(StringBuilder builder,TreeNode<T> node){
    if(node.left!=null){
      checkNode(builder,node.left);
    }
    builder.append(node.value.toString()+" ");
    if(node.right!=null){
      checkNode(builder,node.right);
    }
  }
}
