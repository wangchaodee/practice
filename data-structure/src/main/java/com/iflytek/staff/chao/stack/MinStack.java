package com.iflytek.staff.chao.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStack {

    Stack<Integer> element ;
    Stack<Integer> mins ;

    /** initialize your data structure here. */
    public MinStack() {
        element = new Stack<>();
        mins = new Stack<>();
    }

    /**
     * = 号 不可少，0，1，0 ， pop中会将等值的0弹出，造成后续min为空
     * @param val
     */
    public void push(int val) {
        if(mins.isEmpty() || val <= getMin()){
            mins.push(val);
        }
        element.push(val);
    }

    public void pop() {
        int val = element.pop();
        if(val==getMin()) mins.pop();
    }

    public int top() {
        return element.peek();
    }

    public int getMin() {
        if(mins.isEmpty()){

        }
        return mins.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */