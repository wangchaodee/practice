package com.iflytek.staff.chao.array;

import com.iflytek.staff.chao.algorithm.base.UnionFind;
import org.junit.Test;

/**
 * @author : hamilton
 * @Description: 并查集测试
 * @date Date : 2022年08月03日 18:23
 */
public class UnionFindTest {

    @Test
    public void findCircleNum() {
        UnionFind test = new UnionFind();
        test.findCircleNum(new int[][]{{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}});

    }
}