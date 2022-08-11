package com.iflytek.staff.chao.array;

import junit.framework.TestCase;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2022年07月13日 20:44
 */
public class CharSolutionTest extends TestCase {

    public void testEvalRPN() {
        CharSolution test = new CharSolution();
        test.evalRPN(new String[]{"4","13","5","/","+"});
    }

    public void testBackspaceCompare() {
        CharSolution test = new CharSolution();
        boolean result = test.backspaceCompare("xywrrmp",
                "xywrrmu#p") ;
        System.out.println("result: "+result);
    }
}