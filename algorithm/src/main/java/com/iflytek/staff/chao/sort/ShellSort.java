package com.iflytek.staff.chao.sort;

/**
 * @author : hamilton
 * @Description: 希尔排序  插入排序的改进
 * @date Date : 2022年06月21日 下午6:15
 */
public class ShellSort extends InsertionSort {
    @Override
    public void sort(Comparable[] array) {
        int N = array.length;
        int step = 1;
        while (step < N / 3) step = 3 * step + 1;
        while (step >= 1) {
            for (int i = step; i < N; i++) {
                for (int j = i; j >= step; j -= step) {
                    if (less(array[j], array[j - step])) {
                        exchange(array, j, j - step);
                    }
                }
            }
            step = step / 3;
        }
    }
}
