package com.iflytek.staff.chao.design;

import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {

    private int[] arrays;
    private Random random;

    public ShuffleArray(int[] nums) {
        this.arrays = nums;
        this.random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return Arrays.copyOf(this.arrays, this.arrays.length);
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        random.nextInt();
        return null;

    }

}
