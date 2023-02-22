package com.iflytek.staff.chao.structure.base.list;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author : wangchaodee
 * @Description: 双端队列
 * @date Date : 2023年02月21日 19:21
 */
public class DequeSolution {

    /**
     * 456. 132 模式
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length ;
        if( n< 3 ) return  false ;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[n-1]);
        // 中间值 ，132 的2
        int k = Integer.MIN_VALUE;
        for (int i = n-2; i >=0; i--) {
            //代表一号位的值
            if(nums[i] <k) return true;
            // nums[i] 代表 3号位的值
            while (!stack.isEmpty() && nums[i] > stack.peek()){
                k= Math.max(k,stack.pop());
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
