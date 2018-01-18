package com.codingskills;

/**
 * Created by zhushuangquan on 17/01/2018.
 */
public class BinarySearch {

    public static void run() {
        System.out.println("--- BinarySearch ---");
        System.out.println(binarySerach(new int[]{1, 2 ,10 ,15, 100}, 15));
        System.out.println(binarySerach(new int[]{1, 2 ,10 ,15, 100}, -2));
        System.out.println(binarySerach(new int[]{1, 2 ,10 ,15, 100}, 101));
        System.out.println(binarySerach(new int[]{1, 2 ,10 ,15, 100}, 13));
        System.out.println("======");
        System.out.println(binarySerach(new int[]{}, 13));
        System.out.println(binarySerach(new int[]{12}, 13));
        System.out.println(binarySerach(new int[]{13}, 13));
        System.out.println("======");
        System.out.println(binarySerach(new int[]{12, 13}, 13));
        System.out.println(binarySerach(new int[]{12, 13}, 12));
    }

    /*
    * Searches element k in a sorted array
    * @param arr a sorted array
    * @param k the element to search
    * @return index in arr where k is. -1 if not found.
    * */
    private static int binarySerach(int[] arr, int k) {
        int a = 0;
        int b = arr.length;
        // Loop invariant: [a, b) is a valid range. (a <= b)
        // k may only be within range [a, b).
        while (a < b) {
            int m = a + (b - a) / 2; // (a + b) / 2 may overflow
            // a == b: m = a and m = b
            // b == a + 1: m = a
            // b == a + 2: m = a + 1
            if (k < arr[m]) { // [a, b) + [b, c) = [a, c)
                              // b - a = len([a, b))
                              // [a, a) ==> empty range
                b = m;
            } else if (k > arr[m]) {
                a = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
