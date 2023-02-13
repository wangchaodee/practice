package com.iflytek.staff.chao.algorithm.base;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 数组相关算法
 * @date Date : 2022年07月13日 16:38
 */
public class DFSTest  {

    public void testOpenLock() {
        DFS bst = new DFS();
       List<String> keys =  bst.getNextKeys("0000");
        for (String key: keys) {
            System.out.println(key);
        }
    }

    public void testShortestBridge() {
        DFS bst = new DFS();
        int[][] grid = new int[][]{{0,1,0,0,0},{0,1,0,1,1},{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0}} ;
        bst.shortestBridge(grid);

    }


}