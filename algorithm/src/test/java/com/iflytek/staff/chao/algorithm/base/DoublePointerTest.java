package com.iflytek.staff.chao.algorithm.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : wangchaodee
 * @Description: 测试
 * @date Date : 2023年02月09日 19:15
 */
public class DoublePointerTest {

    DoublePointer test ;

    @Before
    public void init() {
        test = new DoublePointer();
    }

    @Test
    public void testNextGreaterElement() {
        int res = test.nextGreaterElement(12);
        Assert.assertEquals(21,res);
    }
}