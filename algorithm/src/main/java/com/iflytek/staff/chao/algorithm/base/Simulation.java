package com.iflytek.staff.chao.algorithm.base;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : hamilton
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
}
