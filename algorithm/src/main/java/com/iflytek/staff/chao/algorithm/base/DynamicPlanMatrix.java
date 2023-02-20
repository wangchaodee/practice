package com.iflytek.staff.chao.algorithm.base;

/**
 * @author : wangchaodee
 * @Description: 动态规划和矩阵组合场景的算法题
 * @date Date : 2023年02月16日 09:17
 */
public class DynamicPlanMatrix {

    /**
     * 63 统计有多少路径从左上角到右下角   可能存在障碍物
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if(i==0 && j==0) {
                    // 起始位置  dp[0]=1;
                    dp[j]=1;
                }else if (j>0){
                    // 上一行的 j , 加上本行 左边的一位
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    /**
     * 931. 下降路径最小和
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    // 首行 等于自身元素值
                    dp[i][j] = matrix[i][j];
                } else {
                    // 上方的值 转移过来
                    dp[i][j] = dp[i - 1][j] + matrix[i][j];

                    if (j > 0) {
                        // 左上方的值 转移过来
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + matrix[i][j]);
                    }

                    if (j < n - 1) {
                        // 右上方的值 转移过来
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + matrix[i][j]);
                    }
                }
            }
        }
        // 遍历最后一行的最小值
        int min = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }

    public int minFallingPathSum_1(int[][] grid) {
        int n = grid.length;
        int[] dp = new int[n+2];
        dp[0] = dp[n+1] = Integer.MAX_VALUE;
        // 首行
        for (int j = 1; j <=n ; j++) {
            dp[j] = grid[0][j];
        }

        for (int i = 1; i < n; i++) {
            int temp = 0 , last = Integer.MAX_VALUE ;
            for (int j = 1; j <= n; j++) {
                // 上方的非同一列转移的都可以
                temp = dp[j];
                dp[j] = Math.min( Math.min(last,dp[j]),dp[j+1]) + grid[i][j-1];
                last = temp;
            }
        }
        // 遍历最后一行的最小值
        int min = dp[0];
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    /**
     * 1289. 下降路径最小和 II   相邻行的元素不可以在同一列
     *
     * @param grid
     * @return
     */
    public int minFallingPathSum2(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    // 首行 等于自身元素值
                    dp[i][j] = grid[i][j];
                } else {
                    // 上方的值 转移过来
                    dp[i][j] = 20000;

                    for (int k = 0; k < n; k++) {
                        if (j != k) {
                            // 上方的非同一列转移的都可以
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                        }
                    }
                }
            }
        }
        // 遍历最后一行的最小值
        int min = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }

    /**
     * 64 最小路径 从左上角到右下角
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                } else if (i > 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }  else if(j>0) {
                    //j>0
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }


    /**
     * 221. 最大正方形
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length , n = matrix[0].length ;
        int[][] dp = new int[m][n];
        // 边长
        int max =0 ;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i==0 || j ==0){
                    if(matrix[i][j]=='1') {
                        dp[i][j] = 1;
                        max = Math.max(max,dp[i][j]);
                    }
                }else {
                    if(matrix[i][j]=='1') {
                        dp[i][j] =  Math.min(Math.min(dp[i-1][j-1] ,dp[i-1][j]) ,dp[i][j-1]) +1 ;
                        max = Math.max(max,dp[i][j]);
                    }
                }
            }
        }
        // 面积 求平方
        return max*max ;
    }

    /**
     * 1139. 最大的以 1 为边界的正方形
     * @param grid
     * @return
     */
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length ;
        int n = grid[0].length ;
        // 上方1的长度
        int[][] up = new int[m+1][n+1];
        // 左侧1的长度
        int[][] left = new int[m+1][n+1];
        int maxBorder = 0 ;
        for (int i = 1; i <=m ; i++) {
            for (int j = 1; j <= n; j++) {
                if(grid[i-1][j-1] == 1 ){
                    up[i][j] = up[i-1][j] +1 ;
                    left[i][j] = left[i][j-1] +1 ;

                    int border = Math.min(up[i][j] , left[i][j]) ;

                    while ((left[i-border+1][j] <border) || (up[i][j-border+1] < border) ){
                        border--;
                    }

                    maxBorder = Math.max(maxBorder,border);
                }
            }
        }
        return maxBorder*maxBorder ;
    }
}
