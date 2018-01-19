package com.objectoriented;

import com.codingskills.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zhushuangquan on 19/01/2018.
 */
public class LinkedList implements Iterable<Integer> {

    Node head;
    Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void add(int value) {
        Node node = new Node(value);
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    class ListIterator implements Iterator<Integer> {
        Node currentNode;

        public ListIterator(Node head) {
            currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Integer next() {
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            int value = currentNode.getValue();
            currentNode = currentNode.getNext();
            return value;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ListIterator(head);
    }
}
