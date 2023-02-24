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
     * @param grid   m n  不一定相等
     * @return
     */
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n+1] ;

        for (int i = 0; i < n ; i++) {
            int col = i ;

            for (int[] row : grid) {
                int dir = row[col];
                col+=dir;
                if(col<0 || col>=n || dir != row[col] ){
                    col =-1;
                    break;
                }
            }

            ans[i] = col;
        }
        return ans;
    }
}
