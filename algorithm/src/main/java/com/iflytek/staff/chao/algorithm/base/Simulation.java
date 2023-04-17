package com.iflytek.staff.chao.algorithm.base;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : wangchaodee
 * @Description: 模拟
 * @date Date : 2022年07月28日 13:03
 */
public class Simulation {

    /**
     * 报数  剩余一人为胜利者
     *
     * @param n
     * @param k
     * @return
     */
    public int findTheWinner(int n, int k) {
        if (n == 1) return 1;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        while (queue.size() > 1) {

            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }

        return queue.peek();
    }

    /**
     * 1706. 球会落何处
     *
     * @param grid m n  不一定相等
     * @return
     */
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int col = i;

            for (int[] row : grid) {
                int dir = row[col];
                col += dir;
                if (col < 0 || col >= n || dir != row[col]) {
                    col = -1;
                    break;
                }
            }

            ans[i] = col;
        }
        return ans;
    }

    /**
     * 1599. 经营摩天轮的最大利润
     *
     * @param customers
     * @param boardingCost
     * @param runningCost
     * @return
     */
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int ans = -1, maxProfix = 0, totalProfix = 0, operations = 0, customerCount = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            operations++;
            customerCount += customers[i];
            int curCustomers = Math.min(4, customerCount);
            customerCount -= curCustomers;
            totalProfix += curCustomers * boardingCost - runningCost;
            if (totalProfix > maxProfix) {
                maxProfix = totalProfix;
                ans = operations;
            }
        }
        // 无剩余乘客 直接返回结果
        if (customerCount == 0) {
            return ans;
        }
        // 有剩余乘客， 但后续转 亏本 ，直接返回之前的操作值即可， 基本为-1
        int profixPerTime = 4 * boardingCost - runningCost;
        if (profixPerTime <= 0) {
            return ans;
        }
        // 有剩余乘客， 可以继续转 盈利的
        if (customerCount > 0) {
            int fullTimes = customerCount / 4;
            totalProfix += fullTimes * profixPerTime;
            operations += fullTimes;
            if (totalProfix > maxProfix) {
                maxProfix = totalProfix;
                ans = operations;
            }
            int curCustomers = customerCount % 4;
            totalProfix += curCustomers * boardingCost - runningCost;
            operations++;
            if (totalProfix > maxProfix) {
                maxProfix = totalProfix;
                ans = operations;
            }
        }
        return ans;

    }
}
