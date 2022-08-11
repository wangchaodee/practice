package com.iflytek.staff.chao.array;

import static com.iflytek.staff.chao.array.SortUtil.less;
import static com.iflytek.staff.chao.array.SortUtil.exchange;

/**
 * @author : hamilton
 * @Description: 排序
 * @date Date : 2022年06月21日 下午6:03
 */
public class Sort {

    /**
     * 插入排序
     * @param array
     */
    public void insertSort(Comparable[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j - 1])) {
                    exchange(array, j, j - 1);
                }
            }
        }
    }

    /**
     * 选择排序算法
     * @param array
     */
    public void selectSort(Comparable[] array) {
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

    /**
     * 希尔排序  插入排序的改进
     */
    public void shellSort(Comparable[] array) {
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

    /**
     * 迭代式归并排序
     * @param array
     */
    public void mergeBottomSort(Comparable[] array) {
        int N = array.length;
        aux = new Comparable[N];
        for (int step = 1; step < N; step = step + step) {
            for (int lo = 0; lo < N - step; lo += step + step) {
                merge(array, lo, lo + step - 1, Math.min(lo + step + step - 1, N - 1));
            }
        }
    }

    /**
     * 归并排序
     */
    Comparable[] aux;

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


    /**
     * 快速排序
     * @param array
     */
    public void quickSort(Comparable[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(array, lo, hi);
        quickSort(array, lo, j);
        quickSort(array, j + 1, hi);
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

    /**
     *  波浪式排序
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        boolean small =true ;
        int N = nums.length ;

        for (int i = 1; i < N ; i++) {
            if(small){
                if(nums[i]<nums[i-1]){
                    exchange(nums, i,i-1);
                }
            }else {
                if(nums[i]>nums[i-1]){
                    exchange(nums, i,i-1);
                }
            }
            small = !small;
        }
    }

}
