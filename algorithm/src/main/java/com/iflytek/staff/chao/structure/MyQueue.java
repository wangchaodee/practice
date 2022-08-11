package com.iflytek.staff.chao.structure;

import java.util.Stack;

/**
 * 用两个栈实现队列
 */
public class MyQueue {

    Stack<Integer> input;
    Stack<Integer> output;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    /**
     * Push element x to the back of structure.
     */
    public void push(int x) {
        input.push(x);
    }

    /**
     * Removes the element from in front of structure and returns that element.
     */
    public int pop() {
        if (output.isEmpty()) {
            while (!input.isEmpty())
                output.push(input.pop());
        }
        return output.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty())
                output.push(input.pop());
        }
        return output.peek();
    }

    /**
     * Returns whether the structure is empty.
     */
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

}
