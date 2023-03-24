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

    /**
     * 剑指 Offer II 010. 和为 k 的子数组  ,  560
     * 多少个连续字数组 和为k ,, 因为按题意数组不适合排序， 双指针方式不适用 ， 适用前缀后， 固定一端 ，然后遍历求和
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[i];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum_2(int[] nums, int k) {
        int pre = 0 , count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if(map.containsKey(pre-k)){
                count += map.get(pre-k);
            }
            map.put(pre, map.getOrDefault(pre,0)+1);
        }
        return count;
    }


}
