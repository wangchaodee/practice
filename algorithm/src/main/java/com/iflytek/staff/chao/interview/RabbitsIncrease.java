package com.iflytek.staff.chao.interview;

/**
 * @author : wangchaodee
 * @Description: 兔子生育
 */
public class RabbitsIncrease {

    public static void main(String[] args) {

        System.out.println( calculateRabbitsNum(4,10)); //10
        System.out.println( calculateRabbitsNum(5,24)); //325
        System.out.println( calculateRabbitsNum(4,1000));
    }

    /**
     *
     * @param growUpMonth  兔子成熟的时间
     * @param targetMonth  计算兔子总数量时间点
     * @return 返回兔子的总数量
     */
    public static int calculateRabbitsNum(int growUpMonth , int targetMonth){
        if(targetMonth<growUpMonth) return 1 ;

        int[]  rabbits = new int[targetMonth];
        // 未到成熟期  都只有初始的一对兔子
        for (int i = 0; i < growUpMonth; i++) {
            rabbits[i]=1 ;
        }
        // 到成熟期  成熟兔子生新兔子 + 上个月的兔子数量
        for (int i = growUpMonth; i < targetMonth; i++) {
            rabbits[i] = rabbits[i-growUpMonth] + rabbits[i-1];
        }

        return rabbits[targetMonth-1] ;
    }


}
