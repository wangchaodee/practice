package com.iflytek.staff.chao.interview;

import java.util.PriorityQueue;

public class ByteDance {


    public static int[] sort(int[] nums1, int[] nums2, int[] nums3, int N) {
        int[] ret = new int[N * 3];
        int index = 0;
        int a = 0, b = 0, c = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(3);
        priorityQueue.add(nums1[a]);
        priorityQueue.add(nums2[b]);
        priorityQueue.add(nums3[c]);
        while ((a < N || b < N || c < N) && !priorityQueue.isEmpty()) {
            //6种顺序
            int num = priorityQueue.poll();

            if (num == nums1[a]) {
                ret[index++] = num;
                if (a < N - 1) {
                    priorityQueue.add(nums1[++a]);
                }
            }

            if (num == nums2[b]) {
                ret[index++] = num;
                if (b < N - 1) {
                    priorityQueue.add(nums2[++b]);
                }

            }

            if (num == nums3[c]) {
                ret[index++] = num;
                if (c < N - 1) {
                    priorityQueue.add(nums3[++c]);
                }
            }
        }
        return ret;
    }


}
