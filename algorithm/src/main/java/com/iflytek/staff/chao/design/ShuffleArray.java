package com.iflytek.staff.chao.design;

import java.util.Arrays;
import java.util.Random;

/**
 * @author : hamilton
 * @Description: 打乱数组
 *
 * @date Date : 2022年07月12日 10:41
 */
public class ShuffleArray {

    private int[] origin;
    private int[] current;
    private Random random ;

    public ShuffleArray(int[] nums) {
        origin =  nums.clone();
        current = nums ;
        random = new Random();
    }

    public int[] reset() {
        current =  origin.clone();
        return current;
    }

    public int[] shuffle() {
        int i= 0 ;
        for (int j = 0; j < current.length ; j++) {
             i= random.nextInt(j+1);
            exchange(current, i, current.length-i-1);
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
