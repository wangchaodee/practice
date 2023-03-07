package com.iflytek.staff.chao.algorithm.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: 并查集算法
 * @date Date : 2022年08月03日 17:52
 */
public class UnionFind {


    /**
     * 547. 省份数量
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {

        int N = isConnected.length;
        int[] parent = new int[N];
        // 初始化
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        //进行连通
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }

        int count = 0;
        //统计连通分量
        for (int i = 0; i < N; i++) {
            if (parent[i] == i) count++;
        }

        return count;
    }

    /**
     * 连通分量进行合并
     *
     * @param parent
     * @param i
     * @param j
     */
    private void union(int[] parent, int i, int j) {
        parent[find(parent, i)] = find(parent, j);
    }

    /**
     * 查找父节点
     *
     * @param parent
     * @param i
     * @return
     */
    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    public int makeConnected(int n, int[][] connections) {
        int N = connections.length;
        if (N < n - 1) return -1;

        int[] parent = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            union(parent, connections[i][0], connections[i][1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) count++;
        }
        return count - 1;
    }


    /**
     * 947. 移除最多的同行或同列石头
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        UnionFindUtil util = new UnionFindUtil();
        for (int[] stone : stones) {
            util.union(stone[0]+10001, stone[1]);
        }
        return stones.length - util.getCount();
    }

    class UnionFindUtil{
        Map<Integer, Integer> parent;
        int count  ;

        public UnionFindUtil() {
            parent = new HashMap<>() ;
             count = 0 ;
        }

        public int getCount(){
            return count ;
        }

        private void union( int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x==y) return;

            parent.put(x,y);
            count--;
        }


        private int find(int i) {
            if (!parent.containsKey(i)) {
                parent.put(i,i);
                count++;
            }
           if( i != parent.get(i)){
               parent.put(i,find(parent.get(i)));
           }
           return parent.get(i);
        }
    }
}
