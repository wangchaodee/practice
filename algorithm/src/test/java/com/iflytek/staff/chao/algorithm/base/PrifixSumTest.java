package com.iflytek.staff.chao.algorithm.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月16日 11:00
 */
public class PrifixSumTest {

    PrifixSum test;

    @Before
    public void init(){
        test = new PrifixSum();
    }

    @Test
    public void longestWPI() {
        int res =  test.longestWPI(new int[]{10,8,8,12,6,6,7,11,11,9,11});
        System.out.println("res :" + res);
        Assert.assertEquals(11,res);
    }
}