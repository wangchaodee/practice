package com.iflytek.staff.chao.algorithm.base;

import java.util.Arrays;

/**
 * @author : hamilton
 * @Description: 动态规划 简单类型算法题的归集
 * @date Date : 2023年01月19日 15:52
 */
public class DynamicPlanSimple {

/*    斐波那契数列
1. 爬楼梯
2. 强盗抢劫
3. 强盗在环形街区抢劫
4. 信件错排
5. 母牛生产
*/


    /**
     * 70 爬楼梯， 每次只可以走一步，或两步，   n步楼梯，有多少中走法，
     * 变化的斐波拉切数列 问题
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 2];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public int climbStairs2(int n) {
        return fiber2(n, 1, 1);
    }

    private Integer fiber2(int n, int a, int b) {
        if (n <= 1) {
            return 1;
        }
        Integer ret = 0;
        for (int i = 2; i <= n; i++) {
            ret = a + b;
            a = b;
            b = ret;
        }
        return ret;
    }

    public int fiber(int n) {
        if (n < 2) return n;
        int MOD = 1000000007;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
        }
        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        int N = cost.length;
        int[] dp = new int[N + 1];

        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < N; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
        return Math.min(dp[N - 1], dp[N - 2]);
    }

    /**
     * 198 打家劫舍 情况一  一排房子  相邻房子被偷会报警
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[N];
    }

    public int rob_2(int[] nums) {
        int max1 = 0; //没
        int max2 = nums[0];//偷了  nums[0]

        for (int i = 1; i < nums.length; i++) {
            int curr = Math.max(max2, max1 + nums[i]);
            max1 = max2;
            max2 = curr;
        }
        return max2;
    }

    /**
     * 打家劫舍 情况二  围成一圈的房子  相邻房子被偷会报警
     *
     * @param nums
     * @return
     */
    public int robCase2(int[] nums) {
        int N = nums.length;
        if (N == 1) return nums[0];
        int max1 = robCase2(nums, 0, N - 1);
        int max2 = robCase2(nums, 1, N);

        return max1 > max2 ? max1 : max2;
    }

    private int robCase2(int[] nums, int start, int end) {
        int max1 = 0; //没
        int max2 = 0;//偷了

        for (int i = start; i < end; i++) {
            int curr = Math.max(max2, max1 + nums[i]);
            max1 = max2;
            max2 = curr;
        }
        return max2;
    }

    /**
     * 64 最小路径 从左上角到右下角
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                } else if (i > 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    //j>0
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 62 统计有多少路径从左上角到右下角
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }

        return dp[n - 1];
    }


    /**
     * 数组中等差递增子区间的个数
     * 413. Arithmetic Slices (Medium)
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int N = nums.length;
        if (N < 3) return 0;

        int[] dp = new int[N];
        for (int i = 2; i < N; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += dp[i];
        }
        return ans;
    }


    /**
     * 将n 拆分成两个以上的正整数 ， 求 最大的乘积
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {

            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }


    /**
     * 300. 最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        return Arrays.stream(dp).max().orElse(0);
    }

}
