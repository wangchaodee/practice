package com.iflytek.staff.chao.algorithm.base;

import org.junit.Before;
import org.junit.Test;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月13日 11:15
 */
public class DynamicPlanMathTest {

    DynamicPlanMath test ;

    @Before
    public void setUp(){
        test = new DynamicPlanMath() ;
    }

    @Test
    public void testNthUglyNumber() {
        int res = test.nthUglyNumber(1,1,1,1);
        System.out.println(res);
    }

    @Test
    public void getMoneyAmount() {
        int res = test.getMoneyAmount(100);
        System.out.println(res);
    }

}