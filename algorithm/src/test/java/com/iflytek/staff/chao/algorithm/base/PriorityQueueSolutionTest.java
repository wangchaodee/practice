package com.iflytek.staff.chao.algorithm.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月19日 17:53
 */
public class PriorityQueueSolutionTest {

    PriorityQueueSolution test ;

    @Before
    public void setTest(){
        test = new PriorityQueueSolution();
    }

    @Test
    public void maxAverageRatio() {
        int[][] classes = new int[][]{{1,2},{3,5},{2,2}};
       double ans =  test.maxAverageRatio(classes,2);
        System.out.println(ans);
        Assert.assertEquals( 0.78333,ans);
    }
}