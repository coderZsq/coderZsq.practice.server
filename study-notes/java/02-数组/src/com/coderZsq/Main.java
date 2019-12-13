package com.coderZsq;

public class Main {

    public static void main(String[] args) {
        /*
         * 数组的创建
         *
         * 注意
         * 在Java中, 字符数组 != 字符串
         * 字符数组: char[]
         * 字符串: String
         *
         * 推荐使用 char[] arr 格式定义数组
         * 不推荐使用 char arr[] 格式定义数组
         * */
        {
            int[] arr1;
            int[] arr2 = {}; // 空数组
            int arr3[] = {}; // 空数组

            // 定义的时候被指定数组元素
            int[] arr4 = new int[]{1, 2, 3, 4};
            int[] arr5 = {1, 2, 3, 4};

            // 定义的时候指定数组长度
            int[] arr6 = new int[4];
            arr6[0] = 1;
            arr6[1] = 2;
            arr6[2] = 3;
            arr6[3] = 4;

            // 多维数组
            int[][][] arr7;
            int[] arr8[][];
        }

        /*
         * 数组的内存
         *
         * Java的数组属于引用类型
         * 数组元素存储在堆空间 (Heap)
         * Java的堆内存申请会自动进行初始化
         * */
        {
            int[] array = new int[]{11, 22, 33};
            System.out.println(array);
            System.out.println(array.hashCode());
            System.out.println(0x2503dbd3);
            System.out.println(array[0]);
            System.out.println(array[1]);
        }

        /*
         * 数组的遍历
         * */
        {
            int[] arr = {11, 22, 33, 44};

            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }

            for (int ele : arr) {
                System.out.println(ele);
            }
        }
    }
}
