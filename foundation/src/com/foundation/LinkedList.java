package com.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhushuangquan on 16/01/2018.
 */
public class LinkedList {

    public static void run() {
        System.out.println("--- LinkedList ---");

        Node.printLinkedList(createLenkedList(new ArrayList<Integer>()));
        Node.printLinkedList(createLenkedList(Arrays.asList(1)));
        Node.printLinkedList(createLenkedList(Arrays.asList(1, 2, 3, 4, 5)));

        Node.printLinkedList(reverseLinkedList(createLenkedList(new ArrayList<Integer>())));
        Node.printLinkedList(reverseLinkedList(createLenkedList(Arrays.asList(1))));
        Node.printLinkedList(reverseLinkedList(createLenkedList(Arrays.asList(1, 2, 3, 4, 5))));

        Node.printLinkedList(reverseLinkedList2(createLenkedList(new ArrayList<Integer>())));
        Node.printLinkedList(reverseLinkedList2(createLenkedList(Arrays.asList(1))));
        Node.printLinkedList(reverseLinkedList2(createLenkedList(Arrays.asList(1, 2, 3, 4, 5))));

        //Node.printLinkedList(reverseLinkedList(createLargeLinkedList(1000000)));
        //Node.printLinkedList(reverseLinkedList2(createLargeLinkedList(1000000)));

        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(1, 2, 3, 2, 5)), 2));
        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(1, 2, 3, 2, 2)), 2));
        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(1, 2, 3, 2, 2)), 1));
        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(2, 2, 3, 2, 2)), 2));
        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(2, 2, 3, 2, 2)), 2));
        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(2, 2, 2, 2, 2)), 2));
        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(2)), 2));
        Node.printLinkedList(deleteIfEquals(createLenkedList(Arrays.asList(2)), 1));
        Node.printLinkedList(deleteIfEquals(createLenkedList(new ArrayList<Integer>()), 1));
    }

    /*
    * Creates a linked list
    *
    * @param data the data to create the list
    * @return head of the linked list. The returned linked list ends with last node with getNext() == null.
    * */
    private static Node createLenkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        Node firstNode = new Node(data.get(0));
        firstNode.setNext(createLenkedList(data.subList(1, data.size())));
        return firstNode;
    }

    /*
    * Reverses a linked list
    *
    * @param head the linked list to reverse
    * @return head of the reversed linked list
    * */
    private static Node reverseLinkedList(Node head) {
        // size = 0 or size == 1
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    /*
    * 定义循环不变式, 并在循环体每次结束后保持循环不变式
    * 先一般, 后特殊
    * 每次必须向前推进循环不变式中涉及的变量值
    * 每次推进的规模必须为1
    * */
    private static Node reverseLinkedList2(Node head) {
        Node newHead = null;
        Node curHead = head;
        // Loop invariant
        // newHead points to the linked list already reversed.
        // curHead points to the linked list not yet reversed.
        while (curHead != null) {          // curHead being last node
            // Loop invarint holds.
            Node next = curHead.getNext(); // next = null
            curHead.setNext(newHead);      // curHead.next reversed
            newHead = curHead;             // newHead points to last node
            curHead = next;                // curHead = null
            // Loop invarint holds.
        }
        // Loop invarint holds.
        return newHead;
    }

    private static Node createLargeLinkedList(int size) {
        Node prev = null;
        Node head = null;
        for (int i = 1; i <= size; i++) {
            Node node = new Node(i);
            if (prev != null) {
                prev.setNext(node);
            } else  {
                head = node;
            }
            prev = node;
        }
        return head;
    }

    private static Node deleteIfEquals(Node head, int value) {
        while (head != null && head.getValue() == value) {
            head = head.getNext();
        }
        if (head == null) {
            return null;
        }
        Node prev = head;
        // Loop invariant: list nodes from head up to prev has been
        // processed. (Nodes with values equals to value are deleted.)
        while (prev.getNext() != null) {
            if (prev.getNext().getValue() == value) {
                // delete it
                prev.setNext(prev.getNext().getNext());
            } else {
                prev = prev.getNext();
            }
        }
        return head;
    }
}

