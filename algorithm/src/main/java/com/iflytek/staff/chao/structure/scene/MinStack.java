package com.iflytek.staff.chao.structure.scene;

/**
 * 155 最小栈
 * 基于链表方式（增加了个min属性 保存当前最小值） 实现一个栈  计算栈中的最小值
 * 计算已输入数据的最小值
 */
public class MinStack {

    private ListMinNode top;

    public MinStack() {

    }


    public void push(int val) {
        int min = val;
        if (top != null) {
            min = Math.min(val, top.min);
        }
        ListMinNode cur = new ListMinNode(val, min, top);
        top = cur;
    }

    public void pop() {
        ListMinNode next = top.next;
        top.next = null;
        top = next;
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return top.min;
    }


    class ListMinNode {
        int val;
        int min;
        ListMinNode next;

        public ListMinNode(int val, int min, ListMinNode next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

}