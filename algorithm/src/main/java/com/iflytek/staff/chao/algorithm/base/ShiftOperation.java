package com.iflytek.staff.chao.algorithm.base;

/**
 * @author : wangchaodee
 * @Description: 位移运算
 * @date Date : 2022年07月29日 17:01
 */
public class ShiftOperation {
    /**
     * 计算两数范围内所有数字 按位与运算的结果
     *
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        left <<= shift;
        return left;
    }
}
