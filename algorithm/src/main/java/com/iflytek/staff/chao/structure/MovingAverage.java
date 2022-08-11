package com.iflytek.staff.chao.structure;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

    private Queue<Integer> queue;
    private int count;
    private int size;
    private double avg;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        queue = new LinkedList();
        count = 0;
        this.size = size;
    }

    public double next(int val) {

        if (count >= size) {
            int old = queue.poll();
            queue.offer(val);
            avg = (avg * count - old + val) / count;

        } else {
            count++;
            queue.offer(val);
            avg = (avg * (count - 1) + val) / count;
        }


        return avg;
    }
}
