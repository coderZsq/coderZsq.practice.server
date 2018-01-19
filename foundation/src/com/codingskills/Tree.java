package com.codingskills;

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
        System.out.println("======");
        postOrder(createTree("ABDEGCF", "DBGEACF")) ;
        System.out.println();
        postOrder(createTree("", "")) ;
        System.out.println();
        postOrder(createTree("A", "A")) ;
        System.out.println();
        postOrder(createTree("AB", "BA")) ;
        System.out.println();
        System.out.println("======");
        System.out.println(postOrder("ABDEGCF", "DBGEACF"));
        System.out.println(postOrder("", ""));
        System.out.println(postOrder("A", "A"));
        System.out.println(postOrder("AB", "BA"));
        System.out.println("======");
        inOrderTraverse(createSampleTree());
        inOrderTraverse(createTree("", ""));
        inOrderTraverse(createTree("A", "A"));
        inOrderTraverse(createTree("AB", "BA"));
        inOrderTraverse(createTree("ABCD", "DCBA"));
        inOrderTraverse(createTree("ABCD", "ABCD"));
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

    private static TreeNode inOrderNext(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() != null) {
            return inOrderFirst(node.getRight());
        } else {
            while (node.getParent() != null && node.getParent().getRight() == node) {
                node = node.getParent();
            }
            // node.getParent() == null
            // || node is left child of its parent
            return node.getParent();
        }
    }

    private static TreeNode inOrderFirst(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode curNode = node;
        while (curNode.getLeft() != null) {
            curNode = curNode.getLeft();
        }
        return curNode;
    }

    private static void inOrderTraverse(TreeNode root) {
        for (TreeNode node = inOrderFirst(root); node!= null; node = inOrderNext(node)) {
            System.out.print(node.getValue());
        }
        System.out.println();
    }
}
