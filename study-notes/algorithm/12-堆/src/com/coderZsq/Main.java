package com.coderZsq;

import com.coderZsq.heap.BinaryHeap;
import com.coderZsq.printer.BinaryTrees;

public class Main {

    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        BinaryTrees.println(heap);
    }
}
