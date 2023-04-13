package com.iflytek.staff.chao.structure.scene;

import java.util.PriorityQueue;

/**
 * @author : wangchaodee
 * @Description: 剑指 Offer II 059. 数据流的第 K 大数值
 */
class KthLargest2 {

    private PriorityQueue<Integer> pq;
    private int N ;
    private int size;

    public KthLargest2(int k, int[] nums) {
        pq = new PriorityQueue<>();
        size = k;
        N = 0 ;
        for (int i = 0; i < nums.length; i++) {
            insert(nums[i]);
        }
    }

    public void insert(int v) {
        if (N < size) {
            pq.offer(v);
            N++;
        } else {
            if (v > pq.peek()) {
                pq.poll();
                pq.offer(v);
            }
        }

    }

    public int add(int val) {
        insert(val);
        return pq.peek();
    }



}
