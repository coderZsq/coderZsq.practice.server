package com.sq;

import java.util.ArrayList;
import java.util.List;

public class SmartCollection {
    private List<Integer> list;

    public SmartCollection() {
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        SmartCollection o = new SmartCollection();
        for (int i = 0; i < 100; i++) {
            o.add(5);
            o.add(100);
            o.add(50);
        }
    }

    public void add(int num) {
        if (list.size() == 0) {
            list.add(num);
            return;
        }
        int insertIdx = -1;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (num > list.get(i)) {
                insertIdx = i + 1;
                break;
            }
        }
        list.add(insertIdx, num);
        System.out.println(list);
    }

    public int query(int target) {

        return 0;
    }
}
