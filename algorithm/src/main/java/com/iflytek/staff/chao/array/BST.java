package com.iflytek.staff.chao.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : hamilton
 * @Description: 广度和深度搜索
 * @date Date : 2022年06月24日 下午1:10
 */
public class BST {
    // x ,y  点 顺序  上 右 下 左
    int[] x = new int[]{0, 1, 0, -1};
    int[] y = new int[]{1, 0, -1, 0};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int curColor = image[sr][sc];
        if (curColor != color) {
            dst(image, sr, sc, curColor, color);
        }
        return image;
    }


    /**
     * 深度优先搜索
     *
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @param newColor
     */
    public void dst(int[][] image, int sr, int sc, int color, int newColor) {
        int curColor = image[sr][sc];
        if (curColor == color) {
            image[sr][sc] = newColor;
            for (int i = 0; i < 4; i++) {
                int srr = sr + x[i];
                int scc = sc + y[i];
                if (0 <= srr && srr < image.length && 0 <= scc && scc < image[sr].length) {
                    dst(image, srr, scc, color, newColor);
                }
            }
        }
    }


    public int[][] floodFill2(int[][] image, int sr, int sc, int color) {
        int curColor = image[sr][sc];
        if (curColor == color) {
            return image;
        }

        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            image[s[0]][s[1]] = color;
            bst(image, s[0], s[1], curColor, queue);
        }

        return image;
    }

    /**
     * 广度优先搜索
     *
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @param newColor
     */
    public void bst(int[][] image, int sr, int sc, int color, Queue queue) {

        for (int i = 0; i < 4; i++) {
            int srr = sr + x[i];
            int scc = sc + y[i];
            if (0 <= srr && srr < image.length && 0 <= scc && scc < image[sr].length) {
                if (image[srr][scc] == color) {
                    queue.offer(new int[]{srr, scc});
                }
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {

        int depth = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                depth = Math.max(depth, depth(grid, i, j, n, m));
            }
        }
        return depth;
    }

    private int depth(int[][] grid, int r, int l, int n, int m) {
        if (0 <= r && r < n && 0 <= l && l < m && grid[r][l] == 1) {
            int d = 1;
            grid[r][l] = 0;
            for (int i = 0; i < 4; i++) {
                d += depth(grid, r + x[i], l + y[i], n, m);
            }
            return d;
        } else {
            return 0;
        }
    }

    public int[][] updateMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][m];

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;

                }
            }
        }

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int sr = xy[0];
            int sc = xy[1];
            for (int i = 0; i < 4; i++) {
                int srr = sr + x[i];
                int scc = sc + y[i];
                if (0 <= srr && srr < n && 0 <= scc && scc < m && seen[srr][scc] == false) {
                    queue.offer(new int[]{srr, scc});
                    dist[srr][scc] = dist[sr][sc] + 1;
                    seen[srr][scc] = true;
                }
            }
        }

        return mat;
    }


    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][m];

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int j = 0; j < size; j++) {
                int[] xy = queue.poll();
                int sr = xy[0];
                int sc = xy[1];
                for (int i = 0; i < 4; i++) {
                    int srr = sr + x[i];
                    int scc = sc + y[i];
                    if (0 <= srr && srr < n && 0 <= scc && scc < m && seen[srr][scc] == false && grid[srr][scc] == 1) {
                        queue.offer(new int[]{srr, scc});
                        grid[srr][scc] = 2;
                        seen[srr][scc] = true;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }


        return ans;
    }


    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (r * c != m * n) {
            return mat;
        }
        int a = 0, b = 0;

        int[][] ans = new int[r][c];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a = (i * n + j) / c;
                b = (i * n + j) % c;
                ans[a][b] = mat[i][j];
            }
        }
        return ans;

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] seen = new boolean[n][m];

        int count = 0 ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <m ; j++) {
                if(grid[i][j]=='1' && seen[i][j]==false){
                    //
                    int area = numOfIsland( i ,j , grid, n ,m, seen );

                    count++;
                }
            }
        }
        return  count ;

    }

    private int numOfIsland(int i, int j, char[][] grid, int n , int m ,boolean[][] seen) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        seen[i][j] = true;

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            int[] xy = queue.poll();
            int sr = xy[0];
            int sc = xy[1];
            for (int k = 0; k < 4; k++) {
                int srr = sr + x[k];
                int scc = sc + y[k];
                if (0 <= srr && srr < n && 0 <= scc && scc < m && seen[srr][scc] == false && grid[srr][scc] == '1') {
                    queue.offer(new int[]{srr, scc});
                    seen[srr][scc] = true;
                }
            }
        }

        return  ans ;
    }


}
