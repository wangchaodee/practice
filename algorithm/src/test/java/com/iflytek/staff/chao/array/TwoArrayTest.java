package com.iflytek.staff.chao.array;

import junit.framework.TestCase;

/**
 * @author : hamilton
 * @Description: 二位数组 测试
 * @date Date : 2022年07月28日 23:35
 */
public class TwoArrayTest extends TestCase {

    public void testMinRefuelStops() {
        TwoArray test = new TwoArray();
//        100
//        25
//                [[25,25],[50,25],[75,25]]
        test.minRefuelStops(100,25 ,new int[][]{ {25,25},{50,25},{75,25}});
    }
}