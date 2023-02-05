package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * 数值计算  数学算法
 */
public class Number {


    /**
     * @param n
     * @return
     * @Description 判断n是否3的次方结果
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


    public int countPrimes2(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (isPrimes[i] == false) {
                primes.add(i);
            }

            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                isPrimes[i * primes.get(j)] = true;
                if (i % primes.get(j) == 0) break;
            }
        }
        return primes.size();
    }


    /**
     * 求根
     *
     * @param x
     * @return
     */
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


    /**
     * 梯度排列n各硬币， 可以排列的层级高度
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        int k = 1;
        while (n >= 0) {
            n = n - k;
            k++;
        }
        return k - 1;

    }


    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            res |= (n & 1) << (31 - i);
            n = n >>> 1;
        }
        return res;
    }

    public int countOdds(int low, int high) {
        int interval = high - low + 1;
        if (interval % 2 == 0) {
            return interval / 2;
        } else {
            return interval / 2 + low % 2;
        }
    }

    /**
     * 翻转数字
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long rev = 0;
        int t;
        int i = 0;
        while (x != 0) {
            t = x % 10;
            rev = 10 * rev + t;
            x = x / 10;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) rev;
    }

    /**
     * 以这个数字 每一位上数字的乘积减去汇总和
     *
     * @param n
     * @return
     */
    public int subtractProductAndSum(int n) {
        int multi = 1;
        int total = 0;
        int t = 0;
        while (n != 0) {
            t = n % 10;
            multi *= t;
            total += t;
            n = n / 10;
        }
        return multi - total;
    }

    /**
     * 是否快乐数 ， 快乐数 最终会收敛到1 ， 否则 进入循环
     *
     * @param n 正数
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> cycles = new HashSet<>();
        cycles.add(n);
        while (n != 1) {
            int next = 0;
            int t = n;
            while (t > 0) {
                int y = t % 10;
                next += y * y;
                t /= 10;
            }
            if (cycles.contains(next)) return false;
            cycles.add(next);
            n = next;
        }
        return true;
    }

    /**
     * 是否快乐数
     *
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        Set<Integer> cycles = new HashSet(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

        while (n != 1 && !cycles.contains(n)) {
            int next = 0;
//            int t = n;
            while (n > 0) {
                int y = n % 10;
                next += y * y;
                n /= 10;
            }
            n = next;
        }
        return n == 1;
    }

    /**
     * 最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 算阶乘后 尾数为0的个数
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n / 5 > 0) {
            count += n / 5;
        }
        return count;
    }


    /**
     * 获取杨辉三角 第rowIndex行的那列数组
     *
     * @param rowIndex
     * @return 1
     * 11
     * 121
     * 1331
     * dp[i][j]= dp[i-1][j-1] + dp[i-1][j]    //左右边界只取到一个上层值
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++) {
            ans.add(1);
            for (int j = i - 1; j > 0; j--) {
                ans.set(j, ans.get(j) + ans.get(j - 1));
            }
        }
        return ans;
    }

    /**
     * 2016. 增量元素之间的最大差值
     * 其中 0 <= i < j < n 且 nums[i] < nums[j] 。
     *
     * @param nums
     * @return
     */
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int preMin = nums[0];
        int curMax = -1;
        for (int i = 1; i < n; i++) {
            curMax = Math.max(curMax, nums[i] - preMin);
            preMin = Math.min(preMin, nums[i]);
        }
        return curMax > 0 ? curMax : -1;
    }

    /**
     * 1342. 将数字变成 0 的操作次数
     * @param num
     * @return
     */
    public int numberOfSteps(int num) {
        int cnt = 0 ;
        while (num > 0 ){
            if((num & 1) == num) {
                num--;
            }else {
                num /=2 ;
            }
        }
        return cnt ;
    }
}
