package com.iflytek.staff.chao.array;

import com.iflytek.staff.chao.algorithm.base.StringSolution;
import junit.framework.TestCase;

/**
 * @author : hamilton
 * @Description: 字符串算法测试
 * @date Date : 2022年08月01日 17:56
 */
public class StringSolutionTest extends TestCase {

    public void testGroupAnagrams() {
    }

    public void testGetNext() {
        StringSolution test  = new StringSolution();
        int[] arr = test.getNext("abcabcabcabc");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public void testEvalRPN() {
        StringSolution test = new StringSolution();
        test.evalRPN(new String[]{"4","13","5","/","+"});
    }

    public void testBackspaceCompare() {
        StringSolution test = new StringSolution();
        boolean result = test.backspaceCompare("xywrrmp",
                "xywrrmu#p") ;
        System.out.println("result: "+result);
    }
}