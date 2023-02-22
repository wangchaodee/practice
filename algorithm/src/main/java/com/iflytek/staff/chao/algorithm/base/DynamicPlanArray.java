package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.NumberUtil;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 动态规划  数组
 */
public class DynamicPlanArray {

    /**
     * 42. 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        int preMax = height[0];
        int prei = 0;
        int sum = 0;
        int cur = 0;
        for (int i = 1; i < n; i++) {
            if (height[i] >= preMax) {
                sum += cur;
                cur = 0;
                preMax = height[i];
                prei = i;
            } else {
                cur += preMax - height[i];
            }
        }

        preMax = height[n - 1];
        cur = 0;
        for (int i = n - 2; i >= prei; i--) {
            if (height[i] >= preMax) {
                sum += cur;
                cur = 0;
                preMax = height[i];
            } else {
                cur += preMax - height[i];
            }
        }
        return sum;
    }

    public int trap2(int[] height) {
        int n = height.length;
        int left = 0;
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (height[i] >= height[left]) {
                sum += getSum(height,left,i);
                left = i;
            }
        }

        int right = n-1;
        for (int i = n - 2; i >= left; i--) {
            if (height[i] >= height[right]) {
                sum += getSum(height,i,right);
                right = i;
            }
        }
        return sum;
    }

    private int getSum(int[] height , int l ,int r ){
        int min = Math.min(height[l], height[r]) ;
        int cnt = 0 ;
        for (int i = l+1; i <r ; i++) {
            cnt += min - height[i] ;
        }
        return cnt ;
    }

    /**
     * 1326. 灌溉花园的最少水龙头数目
     * @param n  花园数量 长度
     * @param ranges   水龙头覆盖范围
     * @return
     */
    public int minTaps(int n, int[] ranges) {
        // 前面 i 个 花园  要的水龙头数量
        int[] dp = new int[n+1];
        for (int i = 0; i <=n ; i++) {
            dp[i] = i;
        }

        for (int i = 0; i <=n ; i++) {
            int start = Math.max(i-ranges[i] ,0);
            int end = Math.min(i+ranges[i] ,n);
            dp[start]=Math.max(start,end );
        }
        int last =0 ,ret=0 ,pre=0 ;
        for (int i = 0; i <=n ; i++) {
            last = Math.max(last,dp[i]);
            if(last ==i) return -1 ;
            if(i==pre){
                ret++;
                pre=last;
            }
        }

        return ret ;
    }


    /**
     * 486. 预测赢家
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        return total(nums,0,nums.length-1 ,1) >=0 ;
    }

    private int total(int[] nums , int start ,int end  , int turn){
        if(start==end) return nums[start] * turn ;
        int startScore = nums[start]* turn + total(nums,start+1, end,-turn);
        int endScore = nums[end] * turn+ total(nums,start, end-1,-turn);
        return Math.max(startScore* turn,endScore* turn) * turn;
    }

    public boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        // 逐步扩大选取的数字范围
        for (int i = n-2; i >=0; i--) {
            for (int j = i+1; j < n; j++) {
                                    // 选左边            选右边
                dp[j] = Math.max( nums[i] - dp[j] ,nums[j]- dp[j-1] );
            }
        }
        return dp[n-1] >=0 ;
    }

    /**
     * 877. 石子游戏
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = piles[i];
        }
        // 逐步扩大选取的数字范围
        for (int i = n-2; i >=0; i--) {
            for (int j = i+1; j < n; j++) {
                // 选左边            选右边
                dp[j] = Math.max( piles[i] - dp[j] ,piles[j]- dp[j-1] );
            }
        }
        return dp[n-1] >0 ;
    }

    /**
     * 1140. 石子游戏 II
     *
     * @param piles
     * @return
     */
    public int stoneGameII(int[] piles) {
        int n = piles.length ;
        int[] prefix = new int[n+1] ;
        for (int i = 0; i < n; i++) {
            prefix[i+1] = prefix[i] + piles[i] ;
        }
        Map<Integer,Integer> memo = new HashMap<>();

        return (prefix[n] + dp(prefix,piles,memo ,0,1)) /2 ;
    }

    private int dp(int[] prefix, int[] piles, Map<Integer, Integer> memo, int i, int m) {
        if(i==piles.length) return 0 ;

        int key = 100 *i + m ;
        if(!memo.containsKey(key)){
            int max = Integer.MIN_VALUE ;
            for (int x = 1; x <= 2*m; x++) {
                if(i+x > piles.length ){
                    break;
                }
                max = Math.max(max , prefix[i+x] - prefix[i] -  dp(prefix,piles,memo ,i+x,Math.max(x,m)));
            }
            memo.put(key,max);
        }
        return memo.get(key);
    }

}
