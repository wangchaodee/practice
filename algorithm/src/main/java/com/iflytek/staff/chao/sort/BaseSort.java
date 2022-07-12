package com.iflytek.staff.chao.sort;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2022年06月20日 下午4:02
 */
public abstract class BaseSort {
    public abstract void sort(Comparable[] array);

    protected static boolean less(Comparable l, Comparable r) {
        return l.compareTo(r) < 0;
    }

    protected void exchange(Comparable[] array, int i, int j) {
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

    public void verify(Comparable[] array) {
        sort(array);
        assert isSorted(array);
        show(array);
    }

}
