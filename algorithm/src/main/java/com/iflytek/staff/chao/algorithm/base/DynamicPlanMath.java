package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.NumberUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 动态规划 简单类型 如 数字 数学相关 算法题的归集
 * @date Date : 2023年01月19日 15:52
 */
public class DynamicPlanMath {

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
        dp[0] = 1;
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
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int N = cost.length;
        int[] dp = new int[N + 1];

        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[N];
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

        return Math.max(max1, max2);
    }

    private int robCase2(int[] nums, int start, int end) {
        int max1 = 0; //没
        int max2 = nums[start];//偷了

        for (int i = start + 1; i < end; i++) {
            int curr = Math.max(max2, max1 + nums[i]);
            max1 = max2;
            max2 = curr;
        }
        return max2;
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
     * 剑指 Offer 14- I. 剪绳子
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n, int m) {
        int[] dp = new int[m + 1];
        dp[1] = 1;
        for (int i = 2; i <= m; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[m];
    }

    /**
     * 剑指 Offer 14- II. 剪绳子 II
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int mod = (int) 1e9 + 7;
        long res = 1;
        while (n > 4) {
            res *= 3;
            res %= mod;
            n -= 3;
        }
        return (int) (res * n % mod);
    }

/**
 * 分割整数
 * 1. 分割整数的最大乘积
 * 2. 按平方数来分割整数
 * 3. 分割整数构成字母字符串
 */
    /**
     * 279 完全平方数
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> sqList = generateSquareList(n);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[1] = 1;
        for (int square : sqList) {
            for (int i = square; i <= n; i++) {
                dp[i] = Math.min(dp[i], dp[i - square] + 1);
            }
        }
        return dp[n];
    }

    private List<Integer> generateSquareList(int n) {
        List<Integer> sqList = new ArrayList<>();
        int diff = 3;
        int square = 1;
        while (square <= n) {
            sqList.add(square);
            square += diff;
            diff += 2;
        }
        return sqList;
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

    public int lengthOfLIS_2(int[] nums) {
        int[] sort = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            int l = 0, r = res;
            //[l,r)  右边不包含
            while (l < r) {
                int m = (l + r) >> 1;
                if (sort[m] < num) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            sort[l] = num;
            // 说明 sort中值小 l指针增大到了r ,右边扩展填充了个值 ，个数就增加一个
            if (r == res) res++;
        }
        return res;
    }


    /**
     * 646. 最长数对链
     *
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * 376. 摆动序列
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    public int wiggleMaxLength_2(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;
        int prediff = nums[1] - nums[0];
        int cnt = (prediff != 0) ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prediff <= 0) || (diff < 0 && prediff >= 0)) {
                cnt++;
                prediff = diff;
            }
        }
        return cnt;
    }

    /**
     * 650. 只有两个键的键盘
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        if (n == 1) return 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return i + minSteps(n / i);
        }
        return n;
    }

    public int minSteps2(int n) {
        int h = (int) Math.sqrt(n);
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= h; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    /**
     * 264. 丑数 II
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p1 = 1, p2 = 1, p3 = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(Math.min(dp[p1] * 2, dp[p2] * 3), dp[p3] * 5);
            if (dp[i] == dp[p1] * 2) p1++;
            if (dp[i] == dp[p2] * 3) p2++;
            if (dp[i] == dp[p3] * 5) p3++;
        }
        return dp[n];
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
//        int ans=0 ;
        long l = 1, r = Integer.MAX_VALUE;
        long ab = NumberUtil.lcm(a, b);
        long ac = NumberUtil.lcm(a, c);
        long bc = NumberUtil.lcm(b, c);
        long abc = NumberUtil.lcm(b, ac);

        while (l <= r) {
            long mid = (r - l) / 2 + l;
            long N = mid / a + mid / b + mid / c - mid / ab - mid / ac - mid / bc + mid / abc;
            if (N < n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) l;
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
                if (end >= nums.length) break;
            }
        }
        return step;
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
     * 740. 删除并获得点数
     *
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
            cost[num] += num;
        }

        int first = cost[1];
        int second = Math.max(first, cost[2]);
        for (int j = 3; j <= max; j++) {
            int t = second;
            second = Math.max(first + cost[j], second);
            first = t;
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
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    public int maxProfit3_3(int[] prices) {
        int buy = -prices[0]; // 拥有股票
        int sell = 0; // 无股票
        int coldSell = 0; // 无股票 且在冷冻期

        for (int i = 1; i < prices.length; i++) {

            int newSell = Math.max(sell, buy + prices[i]);
            int newBuy = Integer.MIN_VALUE;
            if (i < 2) {
                newBuy = Math.max(buy, -prices[i]);
            } else {
                newBuy = Math.max(buy, coldSell - prices[i]);
            }
            buy = newBuy;
            coldSell = sell;
            sell = newSell;

        }
        return sell;
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
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public int maxProfit4_2(int[] prices, int fee) {
        int n = prices.length;
        int a = -prices[0]; // 拥有股票
        int b = 0; // 无股票

        for (int i = 1; i < n; i++) {
            int a_ = Math.max(a, b - prices[i]);
            int b_ = Math.max(b, a + prices[i] - fee);
            a = a_;
            b = b_;
        }
//        return a > b ? a : b;
        return b;
    }

    /**
     * 123. 买卖股票的最佳时机 III   ,只能买卖两次
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;
        for (int curPrice : prices) {
            if (firstBuy < -curPrice) {
                firstBuy = -curPrice;
            }
            if (firstSell < firstBuy + curPrice) {
                firstSell = firstBuy + curPrice;
            }
            if (secondBuy < firstSell - curPrice) {
                secondBuy = firstSell - curPrice;
            }
            if (secondSell < secondBuy + curPrice) {
                secondSell = curPrice + secondBuy;
            }
        }
        return secondSell;
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        int[] sell = new int[k + 1];

        for (int curPrice : prices) {
            for (int i = 1; i <= k; i++) {

                if (buy[i] < sell[i - 1] - curPrice) {
                    buy[i] = sell[i - 1] - curPrice;
                }

                if (sell[i] < buy[i] + curPrice) {
                    sell[i] = buy[i] + curPrice;
                }

            }
        }
        return sell[k];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();
        int[] f = new int[N];

        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < N; i++) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] = f[0] + triangle.get(i).get(0);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, f[i]);
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
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0];// 代表最大值
        int min = nums[0]; // 代表最小值  可能负值
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max_ = Math.max(max * nums[i], Math.max(min * nums[i], nums[i]));
            int min_ = Math.min(max * nums[i], Math.min(min * nums[i], nums[i]));
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
        int max = values[0];
        int ans = 0;
        for (int i = 1; i < values.length; i++) {
            ans = Math.max(ans, max + values[i] - i);
            max = Math.max(max, values[i] + i);
        }
        return ans;
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
     *
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = j + dp[i][j - 1];
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        return dp[1][n];
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
            int j = i - 1;
            while (j > 0) {
                row.set(j, row.get(j) + row.get(j - 1));
                j--;
            }
            ans.add(new ArrayList<>(row));
        }
        return ans;
    }

    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < str.length(); i++) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) continue;
            String pre = str.substring(i - 1, i + 1);
            if (pre.compareTo("10") >= 0 && pre.compareTo("25") <= 0) {
                r += p;
            }
        }
        return r;
    }

}
