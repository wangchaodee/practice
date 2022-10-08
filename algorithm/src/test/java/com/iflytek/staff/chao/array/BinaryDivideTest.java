package com.iflytek.staff.chao.array;

import junit.framework.TestCase;

/**
 * @author : hamilton
 * @Description: 二分法测试类
 * @date Date : 2022年07月21日 22:12
 */
public class BinaryDivideTest extends TestCase {

    public void testWaysToSplit() {


          BinaryDivide  test = new BinaryDivide();
          test.waysToSplit(new int[]{1,1,1,2,2,4,2,5,3,8});
    }

    public void testMaxProfitAssignment() {

        BinaryDivide  test = new BinaryDivide();
        test.maxProfitAssignment(new int[]{66,1,28,73,53,35,45,60,100,44,59,94,27,88,7,18,83,18,72,63} ,
                new int[]{66,20,84,81,56,40,37,82,53,45,43,96,67,27,12,54,98,19,47,77},
                new  int[]{61,33,68,38,63,45,1,10,53,23,66,70,14,51,94,18,28,78,100,16});

//        [66,1,28,73,53,35,45,60,100,44,59,94,27,88,7,18,83,18,72,63]
//[66,20,84,81,56,40,37,82,53,45,43,96,67,27,12,54,98,19,47,77]
//[61,33,68,38,63,45,1,10,53,23,66,70,14,51,94,18,28,78,100,16]
    }

    public void testNthUglyNumber() {
        BinaryDivide  test = new BinaryDivide();
        int res = test.nthUglyNumber(1,1,1,1);
        System.out.println(res);
    }
}