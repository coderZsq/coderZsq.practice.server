package com.coderZsq.sort;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对[begin, end)范围的数据进行归并排序
     * */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;

        int mid = (end - begin) >> 1 + begin;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将[begin, mid)和[mid, end)范围的序列合并成一个有序序列
     * */
    private void merge(int begin, int mid, int end) {

    }
}
