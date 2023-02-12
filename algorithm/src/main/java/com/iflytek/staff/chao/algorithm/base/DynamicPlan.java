package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.structure.base.tree.TreeNode;

import java.util.*;

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
     * 45 跳跃到最后节点 需要的最少次数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int start = 0;
        int end = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            start = Math.max(start, i + nums[i]);
            if (i == end) {
                end = start;
                step++;
                if(end >=nums.length) break;
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
     * 583 只允许删除操作  ，求最小删除次数
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
     * 72. 编辑距离
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
     * 740. 删除并获得点数
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
        dp[1] = numCount[1];
        for (int j = 2; j <= max; j++) {
            dp[j] = Math.max(dp[j - 2] + numCount[j] * j, dp[j - 1]);
        }
        return dp[max];
    }

    public int deleteAndEarn2(int[] nums) {
        int[] cost = new int[10001];
        int max = 0;
        for (int num : nums) {
            if (num > max) max = num;
            cost[num] +=num;
        }

        int first = cost[1];
        int second = Math.max(first,cost[2]);
        for (int j = 3; j <= max; j++) {
            int t = second ;
            second = Math.max(first + cost[j], second);
            first = t ;
        }
        return second;
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

    public int maxProfit3_3(int[] prices) {
        int buy = -prices[0]; // 拥有股票
        int sell = 0; // 无股票
        int coldSell = 0; // 无股票 且在冷冻期

        for (int i = 1; i < prices.length; i++) {

            int newSell = Math.max(sell,buy + prices[i]);
            int newBuy = Integer.MIN_VALUE;
            if(i<2){
                newBuy = Math.max(buy,  -prices[i]);
            }else {
                newBuy = Math.max(buy, coldSell - prices[i]);
            }
             buy = newBuy;
             coldSell = sell;
             sell = newSell;

        }
        return sell ;
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
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

    public int maxProfit4_2(int[] prices, int fee) {
        int n = prices.length;
        int a = -prices[0]; // 拥有股票
         int b = 0; // 无股票

        for (int i = 1; i < n; i++) {
            int a_ = Math.max(a, b - prices[i]);
            int b_ = Math.max(b, a + prices[i] - fee);
            a=a_;
            b=b_;
        }
//        return a > b ? a : b;
        return b;
    }

    /**
     * 123. 买卖股票的最佳时机 III   ,只能买卖两次
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE ,firstSell= 0  ;
        int secondBuy  = Integer.MIN_VALUE  ,  secondSell =0 ;
        for (int curPrice : prices) {
           if(firstBuy < -curPrice ) {
               firstBuy= -curPrice ;
           }
           if(firstSell < firstBuy + curPrice ){
               firstSell= firstBuy + curPrice ;
           }
           if(secondBuy < firstSell -curPrice ){
               secondBuy = firstSell -curPrice ;
           }
            if(secondSell < secondBuy + curPrice ){
                secondSell = curPrice + secondBuy ;
            }
        }
        return secondSell ;
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int[] buy = new int[k+1] ;
        Arrays.fill(buy ,Integer.MIN_VALUE);
        int[] sell = new int[k+1];

        for (int curPrice : prices) {
            for (int i = 1; i <= k; i++) {

                if (buy[i] < sell[i - 1] - curPrice) {
                    buy[i] = sell[i - 1] - curPrice;
                }

                if(sell[i] < buy[i] + curPrice) {
                    sell[i] = buy[i] + curPrice ;
                }

            }
        }
        return sell[k];
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
     * 931. 下降路径最小和
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

    public int minFallingPathSum_1(int[][] grid) {
        int n = grid.length;
        int[] dp = new int[n+2];
        dp[0] = dp[n+1] = Integer.MAX_VALUE;
        // 首行
        for (int j = 1; j <=n ; j++) {
            dp[j] = grid[0][j];
        }

        for (int i = 1; i < n; i++) {
             int temp = 0 , last = Integer.MAX_VALUE ;
            for (int j = 1; j <= n; j++) {
                // 上方的非同一列转移的都可以
                temp = dp[j];
                dp[j] = Math.min( Math.min(last,dp[j]),dp[j+1]) + grid[i][j-1];
                last = temp;
            }
        }
        // 遍历最后一行的最小值
        int min = dp[0];
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    /**
     * 1289. 下降路径最小和 II   相邻行的元素不可以在同一列
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
     * 918 环形数组的 最大子序列和
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
        int min = 0;

        for (int i = 1; i < n - 1; i++) {
            dp2[i] = Math.min(dp2[i - 1] + nums[i], nums[i]);
            min = Math.min(min, dp2[i]);
        }
        return Math.max(max, sum - min);
    }

    /**
     * 152. 乘积最大子数组
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0];// 代表最大值
        int min = nums[0]; // 代表最小值  可能负值
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max_  = Math.max(max * nums[i], Math.max(min * nums[i], nums[i]));
            int min_= Math.min(max * nums[i], Math.min(min * nums[i], nums[i]));
            max = max_;
            min = min_;
            ans = Math.max(ans, max);
        }
        return ans;
    }

    /**
     * 1567. 乘积为正数的最长子数组长度
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
     * 1014 最大景点对 得分
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

    /**
     * 最大化 values[i]+i+values[j]−j
     * 的值其实就等价于求 [0,j−1] 中 values[i]+i的最大值 max
     *
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair2(int[] values) {
        int max = values[0] ;
        int ans = 0;
        for (int i = 1; i < values.length; i++) {
            ans = Math.max(ans, max+ values[i] -i);
            max = Math.max(max,values[i] + i);
        }
        return ans;
    }

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
     * 96 可以组成二叉搜索树的种类
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
     * 375. 猜数字大小 II
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
       int[][] dp = new int[n+1][n+1] ;
        for (int i = n-1; i >=1 ; i--) {
            for (int j = i+1; j <=n ; j++) {
                dp[i][j] = j + dp[i][j-1];
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j] ,k + Math.max(dp[i][k-1] , dp[k+1][j]));
                }
            }
        }
        return dp[1][n] ;
    }

    //118. 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(1);
            int j =i-1 ;
            while (j>0) {
                    row.set(j, row.get(j)+ row.get(j-1)  );
                    j--;
            }
            ans.add(new ArrayList<>(row));
        }
        return ans;
    }


}
