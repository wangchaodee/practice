package com.iflytek.staff.chao.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈的功能
 */
public class MyStack {

    Queue<Integer> hold;
    Queue<Integer> input;

    /** Initialize your data structure here. */
    public MyStack() {
        hold = new LinkedList<>();
        input = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        input.offer(x);
        while (!hold.isEmpty()){
            input.offer(hold.poll());
        }

        Queue<Integer> temp = hold;
        hold= input;
        input= temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return hold.poll();
    }

    /** Get the top element. */
    public int top() {
        return hold.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return hold.isEmpty();
    }
}
