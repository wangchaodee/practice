package com.iflytek.staff.chao.structure.base.list;

import junit.framework.TestCase;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2023年01月10日 19:24
 */
public class StackSolutionTest extends TestCase {

    public void testEvalRPN() {
        StackSolution test = new StackSolution();
        test.evalRPN(new String[]{"4","13","5","/","+"});
    }

}