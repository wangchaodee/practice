package com.iflytek.staff.chao.algorithm.base;

import java.util.List;

/**
 * @author : hamilton
 * @Description: 排序
 * @date Date : 2022年06月20日 下午4:02
 */
public class SortUtil {

    public static boolean less(Comparable l, Comparable r) {
        return l.compareTo(r) < 0;
    }

    public static void exchange(Comparable[] array, int i, int j) {
        if (i == j) return;
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void show(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) return false;
        }
        return true;
    }


    public static boolean less(int l, int r) {
        return l < r;
    }

    public static void exchange(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void exchange(List<Integer> array, int i, int j) {
        if (i == j) return;
        int temp = array.get(i);
        array.set(i ,array.get(j));
        array.set(j,temp) ;
    }

}
