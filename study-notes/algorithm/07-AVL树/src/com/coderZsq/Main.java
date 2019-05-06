package com.coderZsq;

import com.coderZsq.printer.BinaryTrees;
import com.coderZsq.tree.AVLTree;

public class Main {

    static void test1() {
        Integer data[] = new Integer[] {
            7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);
    }

    public static void main(String[] args) {
        test1();
    }
}
