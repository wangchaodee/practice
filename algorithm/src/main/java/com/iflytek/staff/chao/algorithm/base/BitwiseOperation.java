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

    public int[] countBits(int n) {
        int[] result = new int[n+1] ;
        for (int i = 1; i <= n; i++) {
            result[i] = result[i&(i-1)] +1;
        }
        return result;
    }

    /**
     * 查找数组中只存在一个的数字， 用的异或方式  ，其他成组存在的数会消除掉
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    /**
     * 137. 只出现一次的数字 II
     * 剑指 Offer II 004. 只出现一次的数字
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     * @param nums
     * @return
     */
    public int singleNumberII(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums){
            for (int i = 0; i < 32; i++) {
                counts[i] += num & 1;
                num >>>=1;
            }
        }

        int ans =0 ,m=3;
        for (int i = 0; i < 32; i++) {
            ans <<=1;
            ans |= counts[31-i] % m ;
        }
        return ans ;
    }

    /**
     * 剑指 Offer II 005. 单词长度的最大乘积
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int length = words.length ;
        int[] mask = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i] ;
            int len = word.length() ;
            for (int j = 0; j < len; j++) {
                mask[i] |= 1<<(word.charAt(j) -'a');
            }
        }
        int max = 0 ;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if((mask[i] & mask[j]) == 0){
                    max = Math.max( max , words[i].length() * words[j].length());
                }
            }
        }
        return max ;
    }
}
