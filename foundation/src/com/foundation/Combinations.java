package com.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhushuangquan on 16/01/2018.
 */
public class Combinations {

    public static void run() {
        System.out.println("--- Combinations ---");
        combinations(new ArrayList<Integer>(), Arrays.asList(1, 2, 3, 4), 2);
        System.out.println("====================");
        combinations(new ArrayList<Integer>(), new ArrayList<Integer>(), 2);
        System.out.println("====================");
        combinations(new ArrayList<Integer>(), new ArrayList<Integer>(), 0);
        System.out.println("====================");
        combinations(new ArrayList<Integer>(), Arrays.asList(1, 2, 3, 4), 1);
        System.out.println("====================");
        combinations(new ArrayList<Integer>(), Arrays.asList(1, 2, 3, 4), 0);
        System.out.println("====================");
        combinations(new ArrayList<Integer>(), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4);
    }

    /*
    * Generates all combinations and output them, selecting n elements from data.
    * */
    private static void combinations(List<Integer> selected, List<Integer> data, int n) {

        // initial value for recursion
        // how to select elements
        // how to output
        if (n == 0) {
            // output all selected elements
            for (Integer i: selected) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            return;
        }
        if (data.isEmpty()) {
            return;
        }

        // se lect element 0
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);
        // un-select element 0
        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);
    }
}
