package com.iflytek.staff.chao.algorithm.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        dp[0]=1 ;dp[1] = 1;
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

    public int fib(int n) {
        int[] dp = new int[n + 2];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    /**
     * 1137 泰波那契数字  三数相加
     *
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        int[] tb = new int[n + 3];
        tb[1] = tb[2] = 1;
        for (int i = 3; i <= n; i++) {
            tb[i] = tb[i - 3] + tb[i - 2] + tb[i - 1];
        }
        return tb[n];
    }

    /**
     * 746. 使用最小花费爬楼梯
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int N = cost.length;
        int[] dp = new int[N+1 ];

        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i-2] , dp[i - 1]+cost[i-1])  ;
        }
        return dp[N ];
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
     * 343 将n 拆分成两个以上的正整数 ， 求 最大的乘积
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
 * 分割整数
 * 1. 分割整数的最大乘积
 * 2. 按平方数来分割整数
 * 3. 分割整数构成字母字符串
 */
    /**
     * 279 完全平方数
     * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> sqList = generateSquareList(n);
        int[] dp = new int[n+1];
        for (int i = 1 ; i <= n; i++) {
            int min = i ;
            for ( int square : sqList){
                if(square > i) break;
                min = Math.min(min , dp[i-square] +1);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    private List<Integer> generateSquareList(int n){
        List<Integer> sqList= new ArrayList<>();
        int diff =3 ;
        int square = 1 ;
        while (square<=n){
            sqList.add(square);
            square+=diff;
            diff+=2;
        }
        return sqList;
    }

    /**
     * 91 分割整数构成字母字符串
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int N = s.length();
        int[] dp = new int[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            char c = s.charAt(i - 1);
            if (c != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != 0 && ((s.charAt(i - 2) - '0') * 10 + (c - '0') <= 26)) {
                dp[i] += dp[i - 2];
            }

        }
        return dp[N];
    }

//    最长递增子序列
//1. 最长递增子序列
//2. 一组整数对能够构成的最长链
//3. 最长摆动子序列
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

    /**
     * 646. 最长数对链
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length ;

        Arrays.sort(pairs,(a,b)-> a[0] - b[0]);

        int[] dp = new int[n] ;
        Arrays.fill(dp,1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * 376. 摆动序列
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if(nums==null || nums.length ==0) return 0;
        int down = 1, up =1 ;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]) {
                up = down+1;
            } else if(nums[i] < nums[i-1]) {
                down = up+1;
            }
        }
        return Math.max(up,down);
    }

    /**
     * 1143 最长的公共子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

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



}
