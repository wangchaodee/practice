package com.iflytek.staff.chao.algorithm.base;

/**
 * @author : wangchaodee
 * @Description: 前缀和
 * @date Date : 2023年02月14日 10:38
 */
public class PrifixSum {

    /**
     * 1124. 表现良好的最长时间段
     *
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] arr = new int[n+1];
        for (int i = 0; i < n; i++) {
            arr[i+1] = arr[i] +  (hours[i] > 8 ? 1 : -1);
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, i-j);
                }
            }
        }

        return max;
    }
}
