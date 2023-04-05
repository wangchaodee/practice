package com.iflytek.staff.chao.structure.scene;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author : wangchaodee
 * @Description: 剑指 Offer II 042. 最近请求次数
 */
public class RecentCounter {

    private Queue<Integer> queue ;

    public RecentCounter() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t) ;
        while (queue.peek()<t-3000){
            queue.poll();
        }
        return queue.size() ;
    }
}
