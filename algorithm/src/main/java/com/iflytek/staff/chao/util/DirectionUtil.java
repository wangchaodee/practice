package com.iflytek.staff.chao.util;

/**
 * @author : hamilton
 * @Description: 方向移动变量
 * @date Date : 2023年01月19日 15:20
 */
public class DirectionUtil {

    //  上，下  ,左，右，
    public static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // x ,y  点 顺序  上 右 下 左 加上4个对角
    public static int[][] eightDots = new int[][]{{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

}
