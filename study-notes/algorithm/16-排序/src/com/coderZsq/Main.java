package com.coderZsq;

import java.util.Arrays;

import com.coderZsq.sort.*;
import com.coderZsq.tools.Asserts;
import com.coderZsq.tools.Integers;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Main {

    public static void main(String[] args) {
        Integer[] array = Integers.random(50000, 1, 20000);

        testSorts(array,
//                new BubbleSort1(),
//                new BubbleSort2(),
//                new BubbleSort3(),
//                new SelectionSort(),
                new HeapSort(),
//                new InsertionSort1(),
//                new InsertionSort2(),
//                new InsertionSort3(),
                new MergeSort(),
                new QuickSort());
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }

        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

}
