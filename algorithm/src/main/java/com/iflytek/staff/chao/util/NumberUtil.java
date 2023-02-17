package com.iflytek.staff.chao.util;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月16日 21:46
 */
public class NumberUtil {

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    /**
     * 最小公倍数
     * @param a
     * @param b
     * @return
     */
    public static long lcm(long a, long b) {
        return a * b / gcd( a, b);
    }

}
