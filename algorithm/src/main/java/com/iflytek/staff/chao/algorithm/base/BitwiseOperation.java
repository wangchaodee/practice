package com.iflytek.staff.chao.algorithm.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 位运算
 */
public class BitwiseOperation {


    /**
     * 89. 格雷编码
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ans =new ArrayList<>();
        ans.add(0);
        for (int i = 1; i <=n; i++) {
            int s = ans.size();
            for (int j = s-1; j >=0 ; j--) {
                ans.add(ans.get(j) | (1<<(i-1)) );
            }
        }

        return ans ;
    }

    /**
     * 1238. 循环码排列
     * @param n
     * @param start
     * @return
     */
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans =new ArrayList<>();
        ans.add(start);
        for (int i = 1; i <=n; i++) {
            int s = ans.size();
            for (int j = s-1; j >=0 ; j--) {
                ans.add(((ans.get(j)^start) | (1<<(i-1)))^start);
            }
        }

        return ans ;
    }


    /**
     * 982. 按位与为零的三元组
     * @param nums
     * @return
     */
    public int countTriplets(int[] nums) {
        int up = 1<<16;
        int[] cnt = new int[up];
        for (int x : nums) {
            for(int y: nums){
                cnt[x&y]++;
            }
        }
        int ans = 0 ;
        for (int k : nums) {
            for (int i = 0; i <up ; i++) {
                if((k&i)==0){
                    ans +=cnt[i];
                }
            }
        }
        return ans ;
    }
}
