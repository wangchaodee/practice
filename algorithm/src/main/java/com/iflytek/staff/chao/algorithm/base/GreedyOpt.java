package com.iflytek.staff.chao.algorithm.base;

import jdk.internal.org.objectweb.asm.tree.InnerClassNode;

import java.util.PriorityQueue;

/**
 * @author : hamilton
 * @Description: 贪心算法
 * @date Date : 2022年08月05日 16:57
 */
public class GreedyOpt {

    public int magicTower(int[] nums) {
        int sum =1 ;
        for (int i = 0; i < nums.length; i++) {
            sum +=nums[i];
        }
        // 总和不为正  则失败
        if(sum<=0) return  -1 ;
        // 回到初始值
        long blood =1 ;
        PriorityQueue<Integer> pq = new PriorityQueue();
        int count =0 ;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] <0){
               pq.offer(nums[i]);

               if(blood+nums[i]<=0){
                   count++;
                   blood -= pq.poll();
               }

            }
            blood +=nums[i];
        }
        return count ;
    }
}
