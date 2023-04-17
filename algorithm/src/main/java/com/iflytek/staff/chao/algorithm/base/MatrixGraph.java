package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

import static com.iflytek.staff.chao.util.DirectionUtil.directions;

/**
 * @author : wangchaodee
 * @Description: 用矩阵表示的图形
 */
public class MatrixGraph {

    /**
     * 1042. 不邻接植花
     *
     * @param n
     * @param paths
     * @return
     */
    public int[] gardenNoAdj(int n, int[][] paths) {
        // 将paths 形式里的花园一对一关联关系  转化为1对多的边关系  至多 1对3个
        int[][] connections = new int[n + 1][3];
        for (int[] garden : paths) {
            int idx = 0;
            while (connections[garden[0]][idx] != 0) idx++;
            connections[garden[0]][idx] = garden[1];
            idx = 0;
            while (connections[garden[1]][idx] != 0) idx++;
            connections[garden[1]][idx] = garden[0];
        }
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int color = 1;
            while (res[connections[i][0]] == color || res[connections[i][1]] == color || res[connections[i][2]] == color) {
                color++;
            }
            res[i] = color;
        }
        return Arrays.copyOfRange(res, 1, n + 1);
    }

    /**
     * 787. K 站中转内最便宜的航班
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k + 2][n];
        int INF = 10000 * 100;
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][src] = 0;
        //  转机k次 ，代表 搭乘航班最多k+1 次
        for (int i = 1; i <= k + 1; i++) {
            for (int[] flight : flights) {
                int s = flight[0], d = flight[1], cost = flight[2];
                dp[i][d] = Math.min(dp[i][d], dp[i - 1][s] + cost);
            }
        }
        int ans = INF;
        for (int i = 1; i <= k + 1; i++) {
            ans = Math.min(ans, dp[i][dst]);
        }
        return ans == INF ? -1 : ans;
    }

    /**
     * 329. 矩阵中的最长递增路径
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int r = matrix.length, l = matrix[0].length;
        int[][] cnts = new int[r][l];
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < l; j++) {
                ans = Math.max(ans, dfs(matrix, cnts, i, j));
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix, int[][] cnts, int i, int j) {
        if (cnts[i][j] != 0) return cnts[i][j];
        cnts[i][j]++;
        for (int[] dir : directions) {
            int srr = i + dir[0];
            int scc = j + dir[1];

            if (0 <= srr && srr < matrix.length && 0 <= scc && scc < matrix[0].length && matrix[srr][scc] > matrix[i][j]) {
                cnts[i][j] = Math.max(cnts[i][j], dfs(matrix, cnts, srr, scc) + 1);
            }
        }
        return cnts[i][j];
    }

    /**
     * LCP 62. 交通枢纽
     *
     * @param path
     * @return
     */
    public int transportationHub(int[][] path) {
        int[] indegree = new int[1001];
        int[] outdegree = new int[1001];
        Set<Integer> points = new HashSet<>();
        for (int[] tmp : path) {
            outdegree[tmp[0]]++;
            indegree[tmp[1]]++;
            points.add(tmp[0]);
            points.add(tmp[1]);
        }
        int match = points.size() - 1;
        for (int i = 0; i < 1001; i++) {
            if (indegree[i] == match && outdegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 2290. 到达角落需要移除障碍物的最小数目
     *
     * @param grid
     * @return
     */
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[0][0] = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});
        while (!deque.isEmpty()) {
            int[] point = deque.pollFirst();
            int x = point[0], y = point[1];
            for (int[] move : directions) {
                int nx = x + move[0], ny = y + move[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int g = grid[nx][ny];
                    if (dis[x][y] + g < dis[nx][ny]) {
                        dis[nx][ny] = dis[x][y] + g;
                        if (g == 1) {
                            deque.addLast(new int[]{nx, ny});
                        } else {
                            deque.addFirst(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        return dis[m - 1][n - 1];
    }

}
