package com.iflytek.staff.chao.interview.design;

import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class ComputerCourse extends AbstractCourse {

    public ComputerCourse() {
        majorName = "计算机" ;
    }

    @Override
    public void printCourseOperation() {
        int m = random.nextInt(10);
        int n = random.nextInt(10);
        int res = (int) (m * Math.pow(2,n));
        System.out.printf( "上课操作：m %s , n %s , 结果 %s \n" , m,n,res );
    }

}
