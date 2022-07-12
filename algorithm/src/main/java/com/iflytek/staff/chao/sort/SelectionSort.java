package com.iflytek.staff.chao.sort;

/**
 * @author : hamilton
 * @Description: 选择排序算法
 * @date Date : 2022年06月21日 下午5:54
 */
public class SelectionSort extends BaseSort {

    @Override
    public void sort(Comparable[] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(array[j], array[min]))
                    min = j;
            }
            exchange(array, i, min);
        }
    }
}
