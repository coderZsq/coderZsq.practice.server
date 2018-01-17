package com.datastructure;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by zhushuangquan on 17/01/2018.
 */
public class Tree {

    public static void run() {
        System.out.println("--- Tree ---");
        preOrder(createSampleTree());
        System.out.println();
        inOrder(createSampleTree());
        System.out.println();
        postOrder(createSampleTree());
        System.out.println();
        System.out.println("============");
        postOrder(createTree("ABDEGCF", "DBGEACF")) ;
        System.out.println();
        postOrder(createTree("", "")) ;
        System.out.println();
        postOrder(createTree("A", "A")) ;
        System.out.println();
        postOrder(createTree("AB", "BA")) ;
        System.out.println();
        System.out.println("============");
        System.out.println(postOrder("ABDEGCF", "DBGEACF"));
        System.out.println(postOrder("", ""));
        System.out.println(postOrder("A", "A"));
        System.out.println(postOrder("AB", "BA"));
    }

    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode('A');
        root.setLeft(new TreeNode('B'));
        root.getLeft().setLeft(new TreeNode('D'));
        root.getLeft().setRight(new TreeNode('E'));
        root.getLeft().getRight().setLeft(new TreeNode('G'));
        root.setRight(new TreeNode('C'));
        root.getRight().setRight(new TreeNode('F'));
        return root;
    }

    private static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.getValue());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    private static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.getLeft());
        System.out.print(root.getValue());
        inOrder(root.getRight());
    }

    private static void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue());
    }

    private static TreeNode createTree(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {
            return null;
        }
        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        TreeNode root = new TreeNode(rootValue);
        root.setLeft(createTree(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex)));
        root.setRight(createTree(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex)));

        return root;
    }

    private static String postOrder(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {
            return "";
        }
        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        return postOrder(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex)) + postOrder(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex)) + rootValue;
    }
}
