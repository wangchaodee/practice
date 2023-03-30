package com.iflytek.staff.chao.interview.design;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class MathCourse extends AbstractCourse {

    public MathCourse() {
        majorName = "数学";
    }

    @Override
    public void printCourseOperation() {
        int m = getRandom() ;
        int rev = 0;
        int t;
        while (m != 0) {
            t = m % 10;
            rev = 10 * rev + t;
            m = m / 10;
        }
        System.out.printf("m : %s , 数字 ： %s" ,m ,rev);
    }

    private int getRandom() {
        int m = random.nextInt(1000);
        if(m<100) return getRandom() ;
        return m ;
    }

}
