package com.iflytek.staff.chao.array;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author : hamilton
 * @Description: 数组相关算法
 * @date Date : 2022年07月13日 16:38
 */
public class BSTTest extends TestCase {

    public void testOpenLock() {
        BST bst = new BST();
       List<String> keys =  bst.getNextKeys("0000");
        for (String key: keys) {
            System.out.println(key);
        }
    }

    public void testShortestBridge() {
        BST bst = new BST();
        int[][] grid = new int[][]{{0,1,0,0,0},{0,1,0,1,1},{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0}} ;
        bst.shortestBridge(grid);

    }

    public void testLadderLength() {
        BST bst = new BST();

        int step = bst.ladderLength("hit",
                "cog",
                Arrays.asList("hot","dot","dog","lot","log","cog") );
        System.out.println(step);
    }
}