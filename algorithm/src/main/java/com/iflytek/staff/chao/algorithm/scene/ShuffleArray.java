package com.iflytek.staff.chao.algorithm.scene;

import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 打乱数组
 * @date Date : 2022年07月12日 10:41
 */
public class ShuffleArray {

    private final int[] origin;
    private int[] current;
    private final Random random;

    public ShuffleArray(int[] nums) {
        origin = nums.clone();
        current = nums;
        random = new Random();
    }

    public int[] reset() {
        current = origin.clone();
        return current;
    }

    public int[] shuffle() {
        int i = 0;
        for (int j = 0; j < current.length; j++) {
            i = random.nextInt(j + 1);
            exchange(current, i, j);
        }

        return current;
    }

    protected void exchange(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
