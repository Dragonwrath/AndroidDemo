package com.example.datastructure;



public class TreeNode<T extends Comparable>{
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T value;

    public TreeNode(T value) {
        this.value = value;
    }

    public void setTreeNode(TreeNode<T> other){
        setTreeNode(this, other);
    }


    @SuppressWarnings("unchecked")
    public void setTreeNode(TreeNode<T> source,TreeNode<T> other) {
        final int i = other.value.compareTo(source.value);
        if (i > 0) {
            if (source.getRight() != null) {
                setTreeNode(source.getRight(),other);
            } else {
                source.right = other;
                other.parent = source;
            }
        } else if (i < 0) {
            if (source.getLeft() != null) {
                setTreeNode(source.getLeft(),other);
            } else {
                source.left = other;
                other.parent = source;
            }
        }
    }

    public T getValue() {
        return value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void printfTree(TreeNode<T> node) {
        if (node.getLeft() != null) {
            printfTree(node.getLeft());
        } else {
            System.out.println(node.value);
        }
        if (node.getRight() != null){
            printfTree(node.getRight());
        } else {
            System.out.println(node.value);
        }

    }
}
