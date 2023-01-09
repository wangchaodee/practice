package com.iflytek.staff.chao.algorithm.base;

/**
 * @author : hamilton
 * @Description: 并查集算法
 * @date Date : 2022年08月03日 17:52
 */
public class UnionFind {


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
        int N  = connections.length ;
        if(N<n-1) return -1 ;

        int[] parent = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            union(parent,connections[i][0],connections[i][1]);
        }

        int count =0 ;
        for (int i = 0; i < n; i++) {
            if(parent[i] == i) count++;
        }
        return count-1;
    }

    // x ,y  点 顺序  上 右 下 左
    int[] x = new int[]{0, 1, 0, -1};
    int[] y = new int[]{1, 0, -1, 0};

}
