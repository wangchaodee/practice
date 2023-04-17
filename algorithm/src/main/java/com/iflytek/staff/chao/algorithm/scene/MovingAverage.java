package com.iflytek.staff.chao.algorithm.scene;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 移动平均值
 */
public class MovingAverage {

    private final Queue<Integer> queue;
    private int count;
    private final int size;
    private double avg;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        queue = new LinkedList();
        count = 0;
        this.size = size;
    }

    /**
     * 新增数据并返回更新后的平均值
     *
     * @param val
     * @return
     */
    public double next(int val) {

        if (count >= size) {
            // 队列已满  取出旧的值，插入新的值，
            int old = queue.poll();
            queue.offer(val);
            //均值计算 原先总值减旧的值再加上新的值 再进行平均
            avg = (avg * count - old + val) / count;
        } else {
            // 队列未满  ，count值自增 ， 插入新的值
            count++;
            queue.offer(val);
            // 均值计算 原先总值 加上新的值 再进行平均
            avg = (avg * (count - 1) + val) / count;
        }
        return avg;
    }
}
