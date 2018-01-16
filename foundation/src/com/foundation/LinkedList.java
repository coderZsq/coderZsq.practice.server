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

}

