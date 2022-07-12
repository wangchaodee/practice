package com.iflytek.staff.chao.sort;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2022年06月21日 下午9:02
 */
public class MergeBottom extends MergeSort {

    public void sort(Comparable[] array) {
        int N = array.length;
        aux = new Comparable[N];
        for (int step = 1; step < N; step = step + step) {
            for (int lo = 0; lo < N - step; lo += step + step) {
                merge(array, lo, lo + step - 1, Math.min(lo + step + step - 1, N - 1));
            }
        }
    }
}
