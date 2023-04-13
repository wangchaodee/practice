package com.iflytek.staff.chao.algorithm.base;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static com.iflytek.staff.chao.algorithm.base.SortUtil.exchange;
import static com.iflytek.staff.chao.algorithm.base.SortUtil.less;

/**
 * @author : wangchaodee
 * @Description: 排序  从小到大
 * @date Date : 2022年06月21日 下午6:03
 */
public class Sort {

    /**
     * 冒泡排序  相邻比较  ，依次将大的值向右边移动，右边尾部有序队列逐步扩展
     *
     * @param array
     */
    public Comparable[] bubbleSort(Comparable[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            boolean flag = true;
            for (int j = 0; j < N - i; j++) {
                if (less(array[j + 1], array[j])) {
                    exchange(array, j, j + 1);
                    flag = false;
                }
            }
            // 代表队列已经有序 ，不需要再检查排序
            if (flag) break;
        }
        return array;
    }

    /**
     * 选择排序算法  每次选出最小值 放入左侧的位置
     *
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
        return array;
    }

    /**
     * 插入排序  左侧有序  将小值插入左侧合适位置
     *
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
        return array;
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
        return array;
    }

    /**
     * 迭代式归并排序
     *
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
        return array;
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
     *
     * @param array
     */
    public Comparable[] quickSort(Comparable[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
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
        for (int i = j; i <= hi; i++) {
            if (less(array[i], array[pivot])) {
                exchange(array, i, j);
                j++;
            }
        }
        exchange(array, pivot, j - 1);
        return j - 1;
    }

    /**
     * 堆排序
     *
     * @param array
     */
    public Comparable[] heapSort(Comparable[] array) {
        int len = array.length;
        buildMaxHeap(array, len);
        for (int i = len - 1; i > 0; i--) {
            exchange(array, 0, i);
            len--;
            heapify(array, 0, len);
        }
        return array;
    }

    private void buildMaxHeap(Comparable[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }


    private void heapify(Comparable[] array, int i, int len) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;
        if (l < len && less(array[largest], array[l])) {
            largest = l;
        }
        if (r < len && less(array[largest], array[r])) {
            largest = r;
        }

        if (largest != i) {
            exchange(array, i, largest);
            heapify(array, largest, len);
        }
    }

    /**
     * 计数排序 与数组下标类型有关联，有局限，当然 可以转换为Map形式sortedMap ,
     *
     * @param array
     */
    public int[] countSort(int[] array) {
        int max = getMaxValue(array);
        return countingSort(array, max);
    }

    private int[] countingSort(int[] array, int max) {
        int bucketLen = max + 1;
        int[] bucket = new int[bucketLen];
        for (int v : array) {
            bucket[v]++;
        }

        int index = 0;
        for (int i = 0; i < bucketLen; i++) {
            while (bucket[i] > 0) {
                array[index++] = i;
                bucket[i]--;
            }
        }

        return array;
    }

    private int getMaxValue(int[] array) {
        int max = array[0];
        for (int v : array) {
            if (less(max, v)) {
                max = v;
            }
        }
        return max;
    }

    /**
     * 波浪式排序
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        boolean small = true;
        int N = nums.length;

        for (int i = 1; i < N; i++) {
            if (small) {
                if (nums[i] < nums[i - 1]) {
                    exchange(nums, i, i - 1);
                }
            } else {
                if (nums[i] > nums[i - 1]) {
                    exchange(nums, i, i - 1);
                }
            }
            small = !small;
        }
    }


/********************************************************************************/


    /**
     * 215. 数组中的第K个最大元素
     * 堆 ：时间复杂度 O(NlogK)，空间复杂度 O(K)。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k + 1);
        for (int j = 0; j < nums.length; j++) {
            queue.offer(nums[j]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    //快速选择 ：时间复杂度 O(N)，空间复杂度 O(1)
    public int findKthLargest2(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] array, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = array[lo];
        while (true) {
            while (less(array[++i], v) && i < hi) ;
            while (less(v, array[--j]) && j > lo) ;
            if (i >= j) break;
            exchange(array, i, j);
        }
        exchange(array, lo, j);
        return j;
    }



    /**
     * 451. 按照字符出现次数对字符串排序
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        int[] cf = new int[128];
        for (char c : s.toCharArray()) {
            cf[c]++;
        }

        // 字符 频率
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int i = 0; i < 128; i++) {
            if (cf[i] > 0) {
                queue.offer(new int[]{i, cf[i]});
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            while (data[1] > 0) {
                ans.append((char) data[0]);
                data[1]--;
            }
        }

        return ans.toString();
    }

    /**
     * 75. 颜色分类
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int N = nums.length;
        int iNum0 = 0, iNum1 = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) {
                SortUtil.exchange(nums, i, iNum1);
                iNum1++;
            } else if (nums[i] == 0) {
                SortUtil.exchange(nums, i, iNum0);
                if (iNum0 < iNum1) {
                    SortUtil.exchange(nums, i, iNum1);
                }
                iNum0++;
                iNum1++;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int N = nums.length;
        int p = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == 0) {
                SortUtil.exchange(nums, i, p);
                p++;
            }
        }

        for (int i = p; i < N; i++) {
            if (nums[i] == 1) {
                SortUtil.exchange(nums, i, p);
                p++;
            }
        }
    }

    public void sortColors3(int[] nums) {
        int z = -1, o = 0, t = nums.length;
        while (o < t) {
            if (nums[o] == 0) {
                // 同步后移 替换的是最左边靠近0的1
                exchange(nums, ++z, o++);
            } else if (nums[o] == 2) {
                // o 还要指向 换完后的数据
                exchange(nums, --t, o);
            } else {
                ++o;
            }
        }
    }

    /**
     * 912. 排序数组  升序
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = pq.poll();
        }
        return nums;
    }

}
