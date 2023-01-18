package com.iflytek.staff.chao.algorithm.scene;

import java.util.Arrays;
import java.util.Random;

/**
 * @author : hamilton
 * @Description: 按概率挑选数字
 * @date Date : 2022年07月25日 10:57
 */
public class PickIndex {

    private int[] preTotal;
    private Random random;
    private int total;

    public PickIndex(int[] w) {
        int N = w.length;
        preTotal = new int[N];
        preTotal[0] = w[0];
        for (int i = 1; i < N; i++) {
            preTotal[i] = preTotal[i - 1] + w[i];
        }
        random = new Random();
        total = preTotal[N - 1];
    }

    public int pickIndex() {
        int N = preTotal.length;
        int bound = random.nextInt(total) + 1;

        int l = 0, r = N - 1, mid = 0;
        while (l < r) {
            mid = (r - l) / 2 + l;
            if (bound <= preTotal[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] sums = new int[(n + 1) * n / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];

                sums[idx++] = sum;
            }

        }

        Arrays.sort(sums);
        int ans = 0;
        for (int i = left - 1; i < right; i++) {
            ans = (ans + sums[i]) % 1000000007;
        }
        return ans;
    }
}
