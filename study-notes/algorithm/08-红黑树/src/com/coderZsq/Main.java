package com.coderZsq;

import com.coderZsq.printer.BinaryTrees;
import com.coderZsq.time.Times;
import com.coderZsq.tree.AVLTree;
import com.coderZsq.tree.BST;
import com.coderZsq.tree.RBTree;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static void test1() {
        Integer data[] = new Integer[] {
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
            System.out.println("[" + data[i] + "]");
            BinaryTrees.println(avl);
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }

        for (int i = 0; i < data.length; i++) {
            avl.remove(data[i]);
            System.out.println("[" + data[i] + "]");
            BinaryTrees.println(avl);
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }

//        BinaryTrees.println(avl);
    }

    static void test2() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100_0000; i++) {
            data.add((int)(Math.random() * 100_0000));
        }

        BST<Integer> bst = new BST<>();
        Times.test("bst.add", new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < data.size(); i++) {
                    bst.add(data.get(i));
                }
            }
        });

        Times.test("bst.contains", new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < data.size(); i++) {
                    bst.contains(data.get(i));
                }
            }
        });

        Times.test("bst.remove", new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < data.size(); i++) {
                    bst.remove(data.get(i));
                }
            }
        });

        AVLTree<Integer> avl = new AVLTree<>();
        Times.test("avl.add", new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < data.size(); i++) {
                    avl.add(data.get(i));
                }
            }
        });

        Times.test("avl.contains", new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < data.size(); i++) {
                    avl.contains(data.get(i));
                }
            }
        });

        Times.test("avl.remove", new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < data.size(); i++) {
                    avl.remove(data.get(i));
                }
            }
        });
    }

    static void test3() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
            System.out.println("[" + data[i] + "]");
            BinaryTrees.println(rb);
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }
//        BinaryTrees.println(rb);
    }

    static void test4() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }
        BinaryTrees.println(rb);
        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("[" + data[i] + "]");
            BinaryTrees.println(rb);
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        test4();
    }
}
