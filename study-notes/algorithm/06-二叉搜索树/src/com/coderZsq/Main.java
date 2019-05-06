package com.coderZsq;

import com.coderZsq.file.Files;
import com.coderZsq.printer.BinaryTreeInfo;
import com.coderZsq.printer.BinaryTrees;
import com.coderZsq.tree.BST;
import com.coderZsq.tree.BinarySearchTree;

import java.util.Comparator;

public class Main {

    private static class PersonComparator implements Comparator<Person> {
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
    }

    static void test2() {

        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new PersonComparator());
        for (int i = 0; i < data.length; i++) {
            bst1.add(new Person(data[i]));
        }

        BinaryTrees.println(bst1);

        /*
         * Java的匿名类, 类似于iOS中的Block, JS中的闭包 (function)
         */

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>(){
            @Override
            public int compare(Person e1, Person e2) {
                return e2.getAge() - e1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        }

        BinaryTrees.println(bst2);
    }

    static void test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            bst.add((int)(Math.random() * 100));
        }

        String str = BinaryTrees.printString(bst);
        str += "\n";
        Files.writeToFile("/Users/zhushuangquan/Desktop/bst.txt", str, true);
//        BinaryTrees.println(bst);
    }

    static void test4() {
        BinaryTrees.println(new BinaryTreeInfo() {
            @Override
            public Object root() {
                return "A";
            }

            @Override
            public Object left(Object node) {
                if (node.equals("A")) return "B";
                if (node.equals("C")) return "E";
                return null;
            }

            @Override
            public Object right(Object node) {
                if (node.equals("A")) return "C";
                if (node.equals("C")) return "D";
                return null;
            }

            @Override
            public Object string(Object node) {
                return node.toString() + "_";
            }
        });
    }

    static void test5() {
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
        bst.add(new Person(10, "jack"));
        bst.add(new Person(12, "rose"));
        bst.add(new Person(6, "jim"));

        bst.add(new Person(10, "michael"));

        BinaryTrees.println(bst);
    }

    static void test6() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        for (int i = 0; i < 40; i++) {
//            bst.add((int)(Math.random() * 100));
//        }

        BinaryTrees.println(bst);
//        bst.preorderTraversal();
//        bst.inorderTraversal();
//        bst.postorderTraversal();
//        bst.levelOrderTraversal();
//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public void visit(Integer element) {
//                System.out.print("_" + element + "_ ");
//            }
//        });

//        bst.inorder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public void visit(Integer element) {
//                System.out.print("_" + element + "_ ");
//            }
//        });

//        System.out.println(bst.height());
        System.out.println(bst.isComplete());
    }

    static void test7() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(7);

        BinaryTrees.println(bst);
    }

    static void test8() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(7);

        BinaryTrees.println(bst);
    }

    public static void main(String[] args) {
        test8();
    }
}
