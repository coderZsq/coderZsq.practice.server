package com.foundation;

/**
 * Created by zhushuangquan on 16/01/2018.
 */
public class Boxing {

    static void run() {
        System.out.println("new Integer(2) == 2 ? " + (new Integer(2) == 2));
        System.out.println("new Integer(2) == new Integer(2) ? " + (new Integer(2) == new Integer(2)));
        System.out.println("Integer.valueOf(2) == Integer.valueOf(2) ? " + (Integer.valueOf(2) == Integer.valueOf(2)));
        System.out.println("Integer.valueOf(1000) == Integer.valueOf(1000) ? " + (Integer.valueOf(1000) == Integer.valueOf(1000)));
        System.out.println("Integer.valueOf(2).intValue() == 2 ? " + (Integer.valueOf(2).intValue() == 2));
        System.out.println("new Integer(2).equals(new Integer(2)) ? " + (new Integer(2).equals(new Integer(2))));
    }
}
