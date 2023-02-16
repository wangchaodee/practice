package com.iflytek.staff.chao.algorithm.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 数字相关 算法测试
 * @date Date : 2022年07月11日 08:06
 */
public class ArrayTraversalTest  {

    ArrayTraversal test;

    @Before
    public void init(){
        test = new ArrayTraversal();
    }

    @Test
    public void testFindClosestElements() {
        List<Integer> stringList = test.findClosestElements(new int[]{3,5,8,10},2,1);
        System.out.println(stringList);
    }

    @Test
    public void testThreeSum() {
        List<List<Integer>> stringList = test.threeSum(new int[]{0,0,0});
        System.out.println(stringList);

    }


}