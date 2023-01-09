package com.iflytek.staff.chao.array;

import com.iflytek.staff.chao.algorithm.base.DFS;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * @author : hamilton
 * @Description: 数组相关算法
 * @date Date : 2022年07月13日 16:38
 */
public class DFSTest extends TestCase {

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

    public void testLadderLength() {
        DFS bst = new DFS();

        int step = bst.ladderLength("hit",
                "cog",
                Arrays.asList("hot","dot","dog","lot","log","cog") );
        System.out.println(step);
    }
}