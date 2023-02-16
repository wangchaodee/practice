package com.iflytek.staff.chao.algorithm.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月13日 11:15
 */
public class DynamicPlanSimpleTest {

    DynamicPlanSimple test ;

    @Before
    public void setUp(){
        test = new DynamicPlanSimple() ;
    }

    @Test
    public void testNthUglyNumber() {
        int res = test.nthUglyNumber(1,1,1,1);
        System.out.println(res);
    }

}