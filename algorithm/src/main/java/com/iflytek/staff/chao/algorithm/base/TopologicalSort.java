package com.iflytek.staff.chao.algorithm.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : wangchaodee
 * @Description: 拓扑排序
 */
public class TopologicalSort {

    /**
     * 207. 课程表
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <numCourses ; i++) {
            edges.add(new ArrayList<>());
        }
        int[] indeg = new int[numCourses];
        int index =0 ;

        for(int[] info : prerequisites){
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(indeg[i]==0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            int u = queue.poll();
            index++;
            for(int v : edges.get(u)){
                --indeg[v];
                if(indeg[v]==0){
                    queue.offer(v);
                }
            }
        }

        if(index!=numCourses) return false;

        return true ;
    }

    /**
     * 210. 课程表 II
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <numCourses ; i++) {
            edges.add(new ArrayList<>());
        }
        int[] indeg = new int[numCourses];
        int[] result = new int[numCourses];
        int index =0 ;

        for(int[] info : prerequisites){
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(indeg[i]==0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            int u = queue.poll();
            result[index++] = u ;
            for(int v : edges.get(u)){
                --indeg[v];
                if(indeg[v]==0){
                    queue.offer(v);
                }
            }
        }

        if(index!=numCourses) return new int[0];

        return result ;
    }
}
