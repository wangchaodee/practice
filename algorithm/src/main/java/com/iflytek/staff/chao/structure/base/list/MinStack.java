package com.iflytek.staff.chao.structure.base.list;

/**
 * 基于链表方式（增加了个min属性 保存当前最小值） 实现一个栈  计算栈中的最小值
 * 计算已输入数据的最小值
 */
public class MinStack {

    private ListMinNode head;

    public MinStack() {

    }


    public void push(int val) {
        int min = val;
        if (head != null) {
            min = Math.min(val, head.min);
        }
        ListMinNode cur = new ListMinNode(val, min, head);
        head = cur;
    }

    public void pop() {
        ListMinNode next = head.next;
        head.next = null;
        head = next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
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