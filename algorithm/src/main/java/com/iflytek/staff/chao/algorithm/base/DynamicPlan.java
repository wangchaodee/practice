package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.structure.base.tree.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hamilton
 * @Description: 动态规划
 * @date Date : 2022年07月15日 16:23
 */
public class DynamicPlan {


    /**
     * 打家劫舍 情况三  房子是二叉树型相邻
     *
     * @param root
     * @return
     */
    Map<TreeNode, Integer> choose = new HashMap<>();
    Map<TreeNode, Integer> ignore = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(choose.getOrDefault(root, 0), ignore.getOrDefault(root, 0));
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        dfs(root.right);

        choose.put(root, root.val + ignore.getOrDefault(root.left, 0) + ignore.getOrDefault(root.right, 0));
        ignore.put(root, Math.max(choose.getOrDefault(root.left, 0), ignore.getOrDefault(root.left, 0))
                + Math.max(choose.getOrDefault(root.right, 0), ignore.getOrDefault(root.right, 0)));
    }

    /**
     * 55 跳跃游戏  贪心算法
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int N = nums.length;
        int i = 0;
        int curMax = nums[0];
        while ( i < curMax && curMax < N-1) {
            i++;
            curMax = Math.max(curMax, i + nums[i]);
        }
        return curMax >= N-1;
    }

    /**
     * 45 跳跃到最后节点 需要的最少次数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int N = nums.length;
        int position = N - 1;
        int step = 0;
        while (position > 0) {
            int i = position - 1;
            while (i >= 0 && i + nums[i] >= position) {
                i--;
            }
            step++;
            position = i + 1;
        }
        return step;
    }

    public int jump2(int[] nums) {
        int N = nums.length;

        int position = 0;
        int end = 0;
        int step = 0;
        for (int i = 0; i < N - 1; i++) {
            position = Math.max(position, i + nums[i]);
            if (i == end) {
                end = position;
                step++;
            }
        }
        return step;
    }


    /**
     * 统计有多少路径从左上角到右下角   可能存在障碍物
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }




    /**
     * 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int N = s.length();

        int len = 0;
        for (String w : wordDict) {
            len = Math.max(len, w.length());
        }

        boolean[] dp = new boolean[N + 1];
        dp[0] = true;
        for (int i = 1; i <= N; i++) {
            int j = Math.max(0, i - len);
            while (j < i) {
                if (dp[j] && checkWord(s.substring(j, i), wordDict)) {
                    dp[i] = true;
                    break;
                }
                j++;
            }
        }
        return dp[N];
    }

    private boolean checkWord(String w, List<String> wordDict) {
        return wordDict.contains(w);
    }

    /**
     * 最长递增子序列的个数
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        int[] cnt = new int[N];
        dp[0] = 1;
        cnt[0] = 1;
        int max = 0;
        int maxcnt = 0;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }

            if (dp[i] > max) {
                max = dp[i];
                maxcnt = cnt[i];
            } else if (dp[i] == max) {
                maxcnt += cnt[i];
            }
        }

        return maxcnt;
    }

    /**
     * 硬币兑换
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


    /**
     * 最长的公共子序列
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

    /**
     * 只允许删除操作  ，求最小删除次数
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m - dp[m][n] + n - dp[m][n];
    }

    /**
     * 可以删除  、修改  、插入 字符， 求最少操作次数
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m * n == 0) {
            return m + n;
        }

        int[][] dp = new int[m + 1][n + 1];
        // 首列差异
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        // 首行差异
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[m][n];
    }




    /**
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int[] numCount = new int[10001];
        int max = 0;
        for (int num : nums) {
            if (num > max) max = num;
            numCount[num]++;
        }

        int[] dp = new int[max + 1];
        dp[0] = 0;
        dp[1] = numCount[1];

        for (int j = 2; j <= max; j++) {
            dp[j] = Math.max(dp[j - 2] + numCount[j] * j, dp[j - 1]);
        }
        return dp[max];

    }

    /**
     * 309 包含冷冻期
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0]; // 拥有股票
        dp[0][1] = 0; // 无股票
        dp[0][2] = 0; // 无股票 且在冷冻期

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        return dp[n - 1][1] > dp[n - 1][2] ? dp[n - 1][1] : dp[n - 1][2];
    }

    /**
     * 714 包含冷冻期
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0]; // 拥有股票
        dp[0][1] = 0; // 无股票

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[n - 1][0] > dp[n - 1][1] ? dp[n - 1][0] : dp[n - 1][1];
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();
        int[][] f = new int[N][N];

        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < N; i++) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int min = 0;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, f[N - 1][i]);
        }
        return min;

    }

    public int minStartValue(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int min = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(nums[i], dp[i - 1] + nums[i]);
            min = Math.min(min, dp[i]);
        }
        if (min < 0) {
            return 1 - min;
        }
        return 1;
    }


    /**
     * 最小下降路径
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    // 首行 等于自身元素值
                    dp[i][j] = matrix[i][j];
                } else {
                    // 上方的值 转移过来
                    dp[i][j] = dp[i - 1][j] + matrix[i][j];

                    if (j > 0) {
                        // 左上方的值 转移过来
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + matrix[i][j]);
                    }

                    if (j < n - 1) {
                        // 右上方的值 转移过来
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + matrix[i][j]);
                    }
                }
            }
        }
        // 遍历最后一行的最小值
        int min = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }

    /**
     * 最小下降路径  相邻行的元素不可以在同一列
     *
     * @param grid
     * @return
     */
    public int minFallingPathSum2(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    // 首行 等于自身元素值
                    dp[i][j] = grid[i][j];
                } else {
                    // 上方的值 转移过来
                    dp[i][j] = 20000;

                    for (int k = 0; k < n; k++) {
                        if (j != k) {
                            // 上方的非同一列转移的都可以
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                        }
                    }
                }
            }
        }
        // 遍历最后一行的最小值
        int min = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }

    /**
     * 环形数组的 最大子序列和
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];

        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);

            sum += nums[i];
        }

        int[] dp2 = new int[n];
        dp2[0] = nums[0];
        int min = dp2[0];

        for (int i = 0; i < n - 1; i++) {
            dp2[i] = Math.min(dp2[i - 1] + nums[i], nums[i]);
            min = Math.min(min, dp2[i]);
        }
        return Math.max(max, sum - min);
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
            dp[i][0] = Math.max(dp[i - 1][1] * nums[i], dp[i][0]);


            dp[i][1] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
            dp[i][1] = Math.min(dp[i - 1][0] * nums[i], dp[i][1]);

            max = Math.max(max, dp[i][0]);
        }
        return max;
    }

    /**
     * 乘积为正数时  最长子串的长度
     *
     * @param nums
     * @return
     */
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int max = 0;
        int neg = 0;
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos = 0;
                neg = 0;
            } else if (nums[i] > 0) {
                pos++;
                neg = neg > 0 ? neg + 1 : 0;
            } else {
                int t = neg;
                neg = pos + 1;
                pos = t > 0 ? t + 1 : 0;
            }
            max = Math.max(max, pos);
        }
        return max;
    }

    /**
     * 最大景点对 得分
     *
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, values[i] + values[j] + i - j);
            }
        }
        return max;
    }

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

    /**
     * 可以组成二叉搜索树的种类
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 以 j为根节点 ， 左右子树数量乘积 的汇总和
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];

    }

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 记录新增的人数
        int[] dp = new int[n + 1 + forget];
        int mod = 1000000007;

        dp[1] = 1;  //知道秘密

        for (int i = 1; i <= n; i++) {
            for (int j = delay; j < forget; j++) {
                dp[i + j] += dp[i];
            }
        }
        int total = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            total = (total + dp[i]) % mod;
        }
        return total;
    }

    /**
     * 32. 最长有效括号
     * @param s
     * @return
     */
//    public int longestValidParentheses(String s) {
//
//    }
}
