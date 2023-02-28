package com.iflytek.staff.chao.algorithm.base;

import java.util.Arrays;

/**
 * @author : wangchaodee
 * @Description: 用矩阵表示的图形
 */
public class MatrixGraph {

    /**
     * 1042. 不邻接植花
     * @param n
     * @param paths
     * @return
     */
    public int[] gardenNoAdj(int n, int[][] paths) {
        // 将paths 形式里的花园一对一关联关系  转化为1对多的边关系  至多 1对3个
        int[][] connections = new int[n+1][3] ;
        for(int[] garden : paths){
            int idx =0 ;
            while (connections[garden[0]][idx] !=0) idx++;
            connections[garden[0]][idx] = garden[1];
            idx=0;
            while (connections[garden[1]][idx] !=0) idx++;
            connections[garden[1]][idx] = garden[0];
        }
        int[] res = new int[n+1] ;
        for (int i = 1; i <=n ; i++) {
            int color =1 ;
            while (res[connections[i][0]] == color || res[connections[i][1]] == color ||res[connections[i][2]] == color){
                color++;
            }
            res[i] = color ;
        }
        return Arrays.copyOfRange(res ,1,n+1);
    }

    /**
     * 787. K 站中转内最便宜的航班
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k+2][n] ;
        int INF = 10000*100 ;
        for (int i = 0; i < k+2; i++) {
            Arrays.fill(dp[i] ,INF );
        }
        dp[0][src] = 0 ;
        //  转机k次 ，代表 搭乘航班最多k+1 次
        for (int i = 1; i <= k+1; i++) {
            for(int[] flight : flights){
                int s = flight[0] , d = flight[1] ,cost=flight[2] ;
                dp[i][d]= Math.min(dp[i][d] , dp[i-1][s] + cost) ;
            }
        }
        int ans = INF;
        for (int i = 1; i <= k+1; i++) {
            ans = Math.min(ans, dp[i][dst]) ;
        }
        return ans==INF ? -1 :ans ;
    }
}
