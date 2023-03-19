package com.iflytek.staff.chao.structure.scene;

import java.util.LinkedList;

/**
 * @author : wangchaodee
 * @Description: 面试题59 - II. 队列的最大值
 *
 */
public class MaxQueue {

    LinkedList<Integer> list ;
    public MaxQueue() {
        list = new LinkedList();
    }

    public int max_value() {
        if(list.isEmpty()) return -1 ;
        int ans = Integer.MIN_VALUE;
        for(Integer num : list){
            if(ans < num) ans= num;
        }
        return ans ;
    }

    public void push_back(int value) {
        list.add(value);
    }

    public int pop_front() {
        if(list.isEmpty()) return -1 ;
        return list.removeFirst();
    }

}
