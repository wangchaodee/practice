package com.iflytek.staff.chao.algorithm.base;


import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : wangchaodee
 * @Description: 动态规划中的背包问题
 * @date Date : 2023年02月16日 14:38
 */
public class DynamicPlanBackPacking {



    // 背包问题
    public int knapsack(int W ,int N , int[] weights ,int[] values ){
        int[][] dp = new int[N+1][W+1] ;
        for (int i = 1; i < N; i++) {
            //w体积成本  ， v 价值
            int w = weights[i]  , v = values[i] ;
            for (int j = 1; j < W; j++) {
                if(j>=w){
                    //能放进去
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w] +v );
                }else {
                    // 放不进去
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[N][W];
    }

    public int knapsack2(int W ,int N , int[] weights ,int[] values ){
        int[] dp = new int[W+1] ;
        for (int i = 1; i < N; i++) {
            //w体积成本  ， v 价值
            int w = weights[i]  , v = values[i] ;
            for (int j = 1; j < W; j++) {
                if(j>=w){
                    //能放进去
                    dp[j] = Math.max(dp[j],dp[j-w] +v );
                }
            }
        }
        return dp[W];
    }
//    变种
//    完全背包：物品数量为无限个
//    多重背包：物品数量有限制
//    多维费用背包：物品不仅有重量，还有体积，同时考虑这两种限制
//    其它：物品之间相互约束或者依赖

    /**
     * 416 分割等和子集
     * @param nums  非空
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = getSum(nums);
        if(sum %2 ==1) return false ;
        int W = sum/2 ;
        boolean[] dp = new boolean[W+1] ;
        dp[0] = true;
        Arrays.sort(nums);
        for (int i = nums.length-1; i >=0; i--) {
            int num = nums[i];
            for (int j = W; j >= num &&  !dp[W]; j--) {
                dp[j] = dp[j] || dp[j-num];
            }
        }
        return dp[W];
    }

    private int getSum(int[] nums){
        int sum=0 ;
        for(int n : nums){
            sum +=n;
        }
        return sum;
    }

    /**
     * 494. 目标和
     * @param nums
     * @param target
     *  sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     *                        2 * sum(P) = target + sum(nums)
     * @return  种类数量
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = getSum(nums) ;
        int diff = sum -target ;
        if(diff<0 || diff %2 ==1) return  0 ;
        int W =  diff /2 ;
        int[] dp = new int[W+1] ;
        dp[0] = 1;
        for (int num : nums) {
            for (int i = W; i >= num ; i--) {
                dp[i] = dp[i] + dp[i-num];
            }
        }
        return dp[W];
    }

    /**
     * 474. 一和零
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String str : strs){
            int zeros =0 , ones = 0;
            for (int i = 0; i < str.length(); i++) {
                if(str.charAt(i) =='0'){
                    zeros++;
                }else {
                    ones++;
                }
            }

            for (int i = m; i >=zeros ; i--) {
                for (int j = n; j >=ones ; j--) {
                    dp[i][j] = Math.max( dp[i][j] ,dp[i-zeros][j-ones] +1) ;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 322 硬币兑换
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int min = amount + 1;
        Arrays.fill(dp, min);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] == min ? -1 : dp[amount];
    }

    public int coinChange_1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int min = amount+1 ;
        Arrays.fill(dp, min);
        Arrays.sort(coins);
        dp[0] = 0;
        for (int coin : coins) {
             for (int i = coin; i <= amount; i++) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == min ? -1 : dp[amount];
    }

    /**
     * 518 硬币兑换  兑换的总数量
     *
     * @param coins
     * @param amount
     * @return
     */
    public int change(int amount, int[] coins) {
        if (amount == 0 || coins == null) return 1;
        int[] dp = new int[amount + 1];
        dp[0] = 1 ;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount] ;
    }


    /**
     * 377 组合总和  涉及顺序的完全背包。
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        if (target == 0 || nums == null) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1 ;
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int j=0 ;j<nums.length && nums[j] <= i;j++) {
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target] ;
    }

}
