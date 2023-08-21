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
     *
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
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        UnionFindUtil util = new UnionFindUtil();
        for (int[] stone : stones) {
            util.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - util.getCount();
    }

    class UnionFindUtil {
        Map<Integer, Integer> parent;
        int count;

        public UnionFindUtil() {
            parent = new HashMap<>();
            count = 0;
        }

        public int getCount() {
            return count;
        }

        private void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if (x == y) return;

            parent.put(x, y);
            count--;
        }


        private int find(int i) {
            if (!parent.containsKey(i)) {
                parent.put(i, i);
                count++;
            }
            if (i != parent.get(i)) {
                parent.put(i, find(parent.get(i)));
            }
            return parent.get(i);
        }
    }


    /**
     * 剑指 Offer II 118. 多余的边
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length ;
        int[] parent = new int[n+1] ;
        for (int i = 1; i <=n ; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i] ;
            int node1 = edge[0] , node2 = edge[1] ;
            if(find(parent, node1) != find(parent, node2)){
                union(parent, node1,node2);
            }else {
                return edge ;
            }
        }
        return new int[0];
    }

    /**
     * 剑指 Offer II 117. 相似的字符串
     * @param strs
     * @return
     */
    int group[] ;
    public int numSimilarGroups(String[] strs) {
        int n = strs.length ;
        int m = strs[0].length() ;
        group = new int[n];
        for (int i = 0; i < n; i++) {
            group[i] =i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <n ; j++) {
                int gi = find(i) , gj= find(j);
                if(gi == gj){
                    continue;
                }
                if(check(strs[i] ,strs[j] ,m)){
                    group[gi] = gj;
                }
            }
        }
        int ret = 0 ;
        for (int i = 0; i < n; i++) {
            if(group[i] ==i){
                ret++;
            }
        }
        return  ret ;
    }

    private int find(int x){
        return  group[x] == x? x : (group[x] = find(group[x]));
    }

    private boolean check(String a , String b ,int len){
        int num = 0 ;
        for (int i = 0; i < len; i++) {
            if(a.charAt(i) != b.charAt(i)){
                num++;
                if(num >2){
                    return false ;
                }
            }
        }
        return true ;
    }
}
