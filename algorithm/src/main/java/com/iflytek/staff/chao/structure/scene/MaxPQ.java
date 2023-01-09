package com.iflytek.staff.chao.structure.scene;

/**
 * @author : hamilton
 * @Description: 堆排序
 * @date Date : 2022年06月23日 下午9:48
 */
public class MaxPQ {

    private int[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = new int[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(int v) {
        pq[++N] = v;
        swim(N);
    }

    public int delMax() {
        int max = pq[1];
        exch(1, N--);
        pq[N + 1] = 0; // 删除这个值
        sink(1);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i] - pq[j] < 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

}


class KthLargest {

    private int[] pq;
    private int N = 0;
    private int size;

    public KthLargest(int k, int[] nums) {
        pq = new int[k + 1];
        size = k;
        for (int i = 0; i < nums.length; i++) {
            insert(nums[i]);
        }
    }

    public void insert(int v) {
        if (N < size) {
            pq[++N] = v;
            swim(N);
        } else {
            if (v > getTop()) {
                pq[1] = v;
                sink(1);
            }
        }

    }

    public int add(int val) {
        insert(val);
        System.out.println(pq[1]);
        return pq[1];
    }

    public void delTop() {
        if (N == size) {
            exch(1, N--);
            sink(1);
        }
    }

    public int getTop() {
        return pq[1];
    }

    private void swim(int k) {
        while (k > 1 && big(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && big(j, j + 1)) j++;
            if (!big(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i] - pq[j] < 0;
    }

    private boolean big(int i, int j) {
        return pq[i] - pq[j] > 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        kthLargest.add(3);   // return 4
        kthLargest.add(5);   // return 5
        kthLargest.add(10);  // return 5
        kthLargest.add(9);   // return 8
        kthLargest.add(4);   // return 8

    }

}

