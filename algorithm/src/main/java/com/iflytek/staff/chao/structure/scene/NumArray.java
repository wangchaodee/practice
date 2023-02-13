package com.iflytek.staff.chao.structure.scene;

/**
 * @author : wangchaodee
 * @Description: 设计一个返回 left 到right 之间的 数据元素的只和
 * @date Date : 2022年07月21日 08:27
 */
public class NumArray {

    private int[] total;

    public NumArray(int[] nums) {
        total = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            total[i + 1] = total[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (right + 2 > total.length || left < 0) return -1;
        return total[right + 1] - total[left];
    }
}
