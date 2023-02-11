package com.iflytek.staff.chao.algorithm.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2023年02月09日 19:06
 */
public class DynamicPlanTest {


    DynamicPlan test ;

    @Before
    public void init() {
        test = new DynamicPlan();
    }

    @Test
    public void getMoneyAmount() {
        int res = test.getMoneyAmount(100);
        System.out.println(res);
    }
}