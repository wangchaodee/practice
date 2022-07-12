package com.iflytek.staff.chao.sort;

/**
 * @author : hamilton
 * @Description: 快速排序
 * @date Date : 2022年06月21日 下午9:11
 */
public class QuickSort extends BaseSort {
    @Override
    public void sort(Comparable[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(array, lo, hi);
        sort(array, lo, j);
        sort(array, j + 1, hi);
    }

    private int partition(Comparable[] array, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = array[lo];
        while (true) {
            while (less(array[++i], v)) if (i == hi) break;
            while (less(v, array[--j])) if (j == lo) break;
            if (i >= j) break;
            exchange(array, i, j);
        }
        exchange(array, lo, j);
        return j;
    }
}
