package com.iflytek.staff.chao.structure.scene;

import java.util.PriorityQueue;

/**
 * @author : wangchaodee
 * @Description: 剑指 Offer 41. 数据流中的中位数
 */
public class MedianFinder {

    PriorityQueue<Integer> queMin ;
    PriorityQueue<Integer> queMax ;

    public MedianFinder() {
        // 小的部分
        queMin = new PriorityQueue<>((a,b) -> b-a);
        // 大的部分
        queMax = new PriorityQueue<>((a,b) -> a-b);
    }

    public void addNum(int num) {
        if(queMin.isEmpty() || num < queMin.peek()){
            queMin.offer(num);
            if(queMax.size()+1 < queMin.size()){
                queMax.offer(queMin.poll());
            }
        }else {
            queMax.offer(num);
            if(queMax.size() > queMin.size()){
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        return queMin.size()> queMax.size()  ? queMin.peek() : (queMin.peek()+ queMax.peek()) /2 ;
    }
}
