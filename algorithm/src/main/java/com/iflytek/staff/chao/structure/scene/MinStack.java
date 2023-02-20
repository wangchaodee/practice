package com.iflytek.staff.chao.structure.scene;

/**
 * 155 最小栈
 * 基于链表方式（增加了个min属性 保存当前最小值） 实现一个栈  计算栈中的最小值
 * 计算已输入数据的最小值
 */
public class MinStack {

    private MinNode top;

    public MinStack() {
        top = null ;
    }


    public void push(int val) {
        int min = val;
        if (top != null) {
            min = Math.min(val, top.min);
        }
        MinNode cur = new MinNode(val, min, top);
        top = cur;
    }

    public void pop() {
        if(top == null) return;
        MinNode next = top.next;
        top.next = null;
        top = next;
    }

    public int top() {
        if(top == null) return -1 ;
        return top.val;
    }

    public int getMin() {
        if(top == null) return -1 ;
        return top.min;
    }


    class MinNode {
        int val;
        int min;
        MinNode next;

        public MinNode(int val, int min, MinNode next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

}