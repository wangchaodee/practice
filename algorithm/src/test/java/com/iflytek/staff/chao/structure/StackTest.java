package com.iflytek.staff.chao.structure;

import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public static  boolean isValid(String s) {
        char[] chars = s.toCharArray() ;
        if(chars.length %2 ==1){
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char c :chars){
            char c2 ;
            switch (c) {
                case  '}':
                    if(stack.isEmpty() || !(stack.pop() =='{')) return  false;
                    continue;
                case  ']':
                    if(stack.isEmpty() || !(stack.pop() =='[')) return  false;
                    continue;
                case  ')':
                    if(stack.isEmpty() || !(stack.pop() =='(')) return  false;
                    continue;
                default:
                    stack.push(c);
                    continue;
            }
        }

        if(stack.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
