package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.NumberUtil;

import java.util.*;

/**
 * 数值计算  数学算法
 */
public class Number {


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

    /**
     * 剑指 Offer 15. 二进制中1的个数
     * 191. 位1的个数
     *
     * @param n
     * @return
     */
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
     *
     * @param num
     * @return
     */
    public int numberOfSteps(int num) {
        int cnt = 0;
        while (num > 0) {
            if ((num & 1) == num) {
                num--;
            } else {
                num /= 2;
            }
        }
        return cnt;
    }

    /**
     * 1250. 检查「好数组」    共同的最小公倍数是否为1
     *
     * @param nums
     * @return
     */
    public boolean isGoodArray(int[] nums) {
        int divi = nums[0];
        for (int i = 1; i < nums.length; i++) {
            divi = NumberUtil.gcd(divi, nums[i]);
            if (divi == 1) break;
        }
        return divi == 1;
    }

    /**
     * 483. 最小好进制
     *
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        Long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
        // m从大到小 ， k 则从小到大
        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0 / m);
            long sum = 1, mul = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) return Integer.toString(k);
        }

        return Long.toString(nVal - 1);
    }

    /**
     * 剑指 Offer 64. 求1+2+…+n
     *
     * @param n
     * @return
     */
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    /**
     * 剑指 Offer 65. 不用加减乘除做加法
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    /**
     * 剑指 Offer 44. 数字序列中某一位的数字
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1, count = 9;
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

    /**
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        long mulk = 1;
        int ans = 0;
        for (int i = 0; n >= mulk; i++) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

    /**
     * 剑指 Offer II 001. 整数除法
     *
     * @param a
     * @param b
     * @return
     */
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE) {
            if (b == 1) return Integer.MIN_VALUE;
            if (b == -1) return Integer.MAX_VALUE;
        }

        if (b == Integer.MIN_VALUE) {
            return a == Integer.MIN_VALUE ? 1 : 0;
        }

        if (a == 0) return 0;

        boolean rev = false;
        if (a > 0) {
            a = -a;
            rev = !rev;
        }
        if (b > 0) {
            b = -b;
            rev = !rev;
        }

        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(b, mid, a);
            if (check) {
                ans = mid;
                if (mid == Integer.MAX_VALUE) break;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rev ? -ans : ans;
    }

    private boolean quickAdd(int b, int mul, int a) {
        // b * mul >=x
        int result = 0, add = b;
        while (mul != 0) {
            if ((mul & 1) != 0) {
                if (result < a - add) {
                    return false;
                }
                result += add;
            }
            if (mul != 1) {
                if (add < a - add) {
                    return false;
                }
                add += add;
            }
            mul >>= 1;
        }
        return true;
    }

    /**
     * LCP 70. 沙地治理
     *
     * @param size
     * @return
     */
    public int[][] sandyLandManagement(int size) {
        int sum = 0;
        for (int i = 1; i <= size; i++) {
            if (i == 1) {
                sum += 1;
                continue;
            }
            if ((i % 2) == (size % 2)) {
                sum += ((size - i) % 4 == 0) ? i : i - 1;
            } else {
                sum += 1;
            }
        }
        int[][] f = new int[sum][2];
        int j = 1;
        set(f, 0, 1, 1);
        for (int i = 2; i <= size; i++) {
            if ((i % 2) != (size % 2)) {
                set(f, j, i, (((size - i) / 2) % 2) == 1 ? 1 : 2);
                j++;
            } else {
                int len = i == 1 ? 1 : ((size - i) % 4 == 0) ? i : i - 1;
                if (len == i) {
                    for (int k = 0; k < len; k++) {
                        set(f, j, i, k * 2 + 1);
                        j++;
                    }
                } else {
                    for (int k = 0; k < len; k++) {
                        set(f, j, i, k * 2 + 3);
                        j++;
                    }
                }
            }
        }
        return f;
    }

    private void set(int[][] f, int idx, int i, int j) {
        f[idx][0] = i;
        f[idx][1] = j;
    }

}
