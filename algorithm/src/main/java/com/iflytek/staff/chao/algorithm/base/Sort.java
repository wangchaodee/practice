package com.iflytek.staff.chao.algorithm.base;

import static com.iflytek.staff.chao.algorithm.base.SortUtil.less;
import static com.iflytek.staff.chao.algorithm.base.SortUtil.exchange;

/**
 * @author : hamilton
 * @Description: 排序  从小到大
 * @date Date : 2022年06月21日 下午6:03
 */
public class Sort {

    /**
     * 冒泡排序  相邻比较  ，依次将大的值向右边移动，右边尾部有序队列逐步扩展
     * @param array
     */
    public Comparable[] bubbleSort(Comparable[] array) {
        int N = array.length;
        for (int i = 1; i <N ; i++) {
            boolean flag= true ;
            for (int j = 0; j <N-i ; j++) {
                if(less(array[j+1], array[j])){
                    exchange(array,j,j+1);
                    flag = false ;
                }
            }
            // 代表队列已经有序 ，不需要再检查排序
            if(flag) break;
        }
        return array ;
    }

    /**
     * 选择排序算法  每次选出最小值 放入左侧的位置
     * @param array
     */
    public Comparable[] selectSort(Comparable[] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(array[j], array[min]))
                    min = j;
            }
            exchange(array, i, min);
        }
        return array ;
    }

    /**
     * 插入排序  左侧有序  将小值插入左侧合适位置
     * @param array
     */
    public Comparable[] insertSort(Comparable[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                // 依次轮换位置
                if (less(array[j], array[j - 1])) {
                    exchange(array, j, j - 1);
                }
                // 也可先保留tmp=array[i]位置，大的值先右移， 找到j后再插入
            }
        }
        return array ;
    }



    /**
     * 希尔排序  插入排序的改进
     */
    public Comparable[] shellSort(Comparable[] array) {
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
        return array ;
    }

    /**
     * 迭代式归并排序
     * @param array
     */
    public Comparable[] mergeBottomSort(Comparable[] array) {
        int N = array.length;
        aux = new Comparable[N];
        for (int step = 1; step < N; step = step + step) {
            for (int lo = 0; lo < N - step; lo += step + step) {
                merge(array, lo, lo + step - 1, Math.min(lo + step + step - 1, N - 1));
            }
        }
        return array ;
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
    public Comparable[] quickSort(Comparable[] array) {
        quickSort(array, 0, array.length - 1);
        return array ;
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

    private int partitionFor(Comparable[] array, int lo, int hi) {
        int pivot = lo, j = pivot + 1;
        Comparable v = array[lo];
        for (int i = j; i <=hi; i++) {
            if(less(array[i],array[pivot])) {
                exchange(array, i, j);
                j++;
            }
        }
        exchange(array, pivot, j-1);
        return j-1;
    }

    /**
     * 堆排序
     * @param array
     */
    public Comparable[] heapSort(Comparable[] array){
        int len = array.length ;
        buildMaxHeap(array, len);
        for (int i = len-1; i >0; i--) {
            exchange(array , 0,i);
            len--;
            heapify(array,0,len);
        }
        return array ;
    }

    private void buildMaxHeap(Comparable[] arr , int len){
        for (int i = (int) Math.floor(len/2); i >=0 ; i--) {
            heapify(arr,i,len);
        }
    }


    private void heapify(Comparable[] array, int i, int len) {
        int l = 2*i + 1;
        int r = 2*i +2 ;
        int largest = i ;
        if(l < len  && less( array[largest], array[l])) {
            largest = l ;
        }
        if(r < len  && less( array[largest], array[r])) {
            largest = r ;
        }

        if(largest !=i ){
            exchange(array,i,largest);
            heapify(array,largest ,len);
        }
    }

    /**
     * 计数排序 与数组下标类型有关联，有局限，当然 可以转换为Map形式sortedMap ,
     * @param array
     */
    public int[] countSort(int[] array){
        int max = getMaxValue(array);
        return countingSort(array,max);
    }

    private int[] countingSort(int[] array , int max){
        int bucketLen = max +1 ;
        int[] bucket = new int[bucketLen];
        for (int v : array) {
             bucket[v]++ ;
        }

        int index=0 ;
        for (int i = 0; i <bucketLen ; i++) {
            while (bucket[i]>0){
                array[index++] = i;
                bucket[i]--;
            }
        }

        return array ;
    }

    private int getMaxValue(int[] array){
        int max = array[0] ;
        for (int v : array){
            if(less(max,v)){
                max= v;
            }
        }
        return max ;
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
