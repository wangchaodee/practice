package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 拓扑排序
 */
public class TopologicalSort {

    /**
     * 207. 课程表
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        int[] indeg = new int[numCourses];
        int index = 0;

        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            index++;
            for (int v : edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) return false;

        return true;
    }

    /**
     * 210. 课程表 II
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        int[] indeg = new int[numCourses];
        int[] result = new int[numCourses];
        int index = 0;

        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            result[index++] = u;
            for (int v : edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) return new int[0];

        return result;
    }

    /**
     * 743. 网络延迟时间
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;

        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            int x = -1;
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;
            for (int y = 0; y < n; y++) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    /**
     * 剑指 Offer II 115. 重建序列
     * @param nums
     * @param sequences
     * @return
     */
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int n = nums.length;
        int[] indegrees = new int[n+1] ;
        Set<Integer>[] graph = new Set[n+1];
        for (int i = 0; i <=n; i++) {
            graph[i] = new HashSet<Integer>();
        }
        for(int[] seq : sequences){
            int size = seq.length;
            for (int i = 1; i <size ; i++) {
                int prev = seq[i-1] , next = seq[i];
                if(graph[prev].add(next)){
                    indegrees[next]++;
                }
            }
        }
        Queue<Integer> queue = new ArrayDeque<>() ;
        for (int i = 1; i <=n ; i++) {
            if(indegrees[i] == 0 ){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            if(queue.size()>1){
                return false;
            }
            int num = queue.poll();
            Set<Integer> set = graph[num];
            for(int next : set){
                indegrees[next]--;
                if(indegrees[next] == 0 ){
                    queue.offer(next);
                }
            }
        }
        return true ;
    }
}
