package com.iflytek.staff.chao.num;

public class Number {

    public static void main(String[] args) {
        System.out.println(sqrt(-1));
        System.out.println(sqrt(0));
        System.out.println(sqrt(2));//1
        System.out.println(sqrt(12));//3
        System.out.println(sqrt(224));//14
    }

    /**
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n > 1) {
            while (n % 3 == 0) n /= 3;
        }
        return n == 1;
    }


    /**
     * 计算小于n内 非负 素数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int[] primes = new int[n + 1];
        int count = 0;

        for (int i = 2; i < n; i++) {
            if (primes[i] == 1) continue;
            count++;
            for (int j = 2 * i; j < n; j += i) {
                primes[j] = 1;
            }
        }
        return count;
    }


    public int rob(int[] nums) {
        int max1 = 0;
        int max2 = 0;

        for (int i = 0; i < nums.length; i++) {
            int curr = Math.max(max2, max1 + nums[i]);
            max1 = max2;
            max2 = curr;
        }
        return max1 > max2 ? max1 : max2;

    }

    /**
     * 卖卖一次股票
     * @param prices
     * @return
     */
//    public int maxProfit(int[] prices) {
//
//    }

    /**
     * 多次卖卖股票
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int max = 0;
        int m = prices.length;
        int l = 0;
        int r = 1;
        while (r < m) {
            if ((prices[r] - prices[r - 1]) > 0) {
                r++;
            } else {
                max += prices[r - 1] - prices[l];
                l = r;
                r++;
            }
        }
        return max;
    }


    /**
     * 爬楼梯， 每次只可以走一步，或两步，   n步楼梯，有多少中走法，
     * 变化的斐波拉切数列 问题
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
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


    public static int sqrt(int x) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }

        return (int) sqrt(2, x);

    }

    public static double sqrt(double i, int x) {

        double res = (i + x / i) / 2;
        if (Math.abs(res - i) < 1) {
            return res;
        } else {
            return sqrt(res, x);
        }
    }
}
