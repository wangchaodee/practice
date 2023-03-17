package com.iflytek.staff.chao.algorithm.base;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 1590. 使数组和能被 P 整除
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        int x = 0 ;
        for (int num : nums ){
            x = (x+num)%p;
        }
        if(x==0) return 0;
        int y=0 , res = nums.length;
        Map<Integer,Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index.put(y,i);
            y= (y+nums[i]) % p ;
            if(index.containsKey((y-x+p)%p)){
                res = Math.min(res, i -index.get((y-x+p)%p) +1 );
            }
        }
        return res == nums.length ? -1 : res ;
    }
}
