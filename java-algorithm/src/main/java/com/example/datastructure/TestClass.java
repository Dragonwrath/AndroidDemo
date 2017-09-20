package com.example.datastructure;


public class TestClass {
    public static void main(String[] args) {
        final TreeNode<Integer> node = new TreeNode<>(10);
        node.setTreeNode(new TreeNode<>(3));
        node.setTreeNode(new TreeNode<>(5));
        node.setTreeNode(new TreeNode<>(15));
        node.setTreeNode(new TreeNode<>(16));
        for (int i = 0; i < 100; i++) {
            node.setTreeNode(new TreeNode<>(i));
        }

        node.printfTree(node);

    }
}
