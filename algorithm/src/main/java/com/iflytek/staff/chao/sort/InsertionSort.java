package com.iflytek.staff.chao.sort;

/**
 * @author : hamilton
 * @Description: 插入排序
 * @date Date : 2022年06月21日 下午6:03
 */
public class InsertionSort extends BaseSort {

    @Override
    public void sort(Comparable[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j - 1])) {
                    exchange(array, j, j - 1);
                }
            }
        }
    }
}
