package com.iflytek.staff.chao.structure.base.list;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : wangchaodee
 * @Description: 堆栈测试
 * @date Date : 2023年01月10日 19:24
 */
public class StackSolutionTest {

    StackSolution test ;

    @Before
    public void init() {
         test = new StackSolution();
    }

    public void testEvalRPN() {
        test.evalRPN(new String[]{"4","13","5","/","+"});
    }

    @Test
    public void nextGreaterElement() {
    }

    @Test
    public void nextGreaterElements() {
    }


}