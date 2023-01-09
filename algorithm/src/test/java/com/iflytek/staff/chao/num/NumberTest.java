package com.iflytek.staff.chao.num;

import com.iflytek.staff.chao.algorithm.base.Number;
import junit.framework.TestCase;

import java.util.List;

/**
 * @author : hamilton
 * @Description: 数字相关 算法测试
 * @date Date : 2022年07月11日 08:06
 */
public class NumberTest extends TestCase {

    public void testFindClosestElements() {
        Number number = new Number();
//        [0,1,2,2,2,3,6,8,8,9]
//        5
//        9
//        List<Integer> stringList = number.findClosestElements(new int[]{0,1,2,2,2,3,6,8,8,9},5,9);
//        System.out.println(stringList);

//        [3,5,8,10]
//        2
//        15

        List<Integer> stringList = number.findClosestElements(new int[]{3,5,8,10},2,1);
        System.out.println(stringList);
    }

    public void testThreeSum() {
        Number number = new Number();
        List<List<Integer>> stringList = number.threeSum(new int[]{0,0,0});
        System.out.println(stringList);

    }
}