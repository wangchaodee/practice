package com.iflytek.staff.chao.sort;

/**
 * @author : hamilton
 * @Description: 归并排序
 * @date Date : 2022年06月21日 下午6:28
 */
public class MergeSort extends BaseSort {

    Comparable[] aux;

    @Override
    public void sort(Comparable[] array) {
        aux = new Comparable[array.length];
        sort(array, 0, array.length - 1);
    }

    protected void merge(Comparable[] array, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) array[k] = aux[j++];
            else if (j > hi) array[k] = aux[i++];
            else if (less(array[j], array[i])) array[k] = aux[j++];
            else array[k] = aux[i++];
        }
    }

    private void sort(Comparable[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(array, lo, mid);
        sort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }


}
