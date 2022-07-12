package com.iflytek.staff.chao.list;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack implements MyList {

    private Node head;
    private int count;

    public MyStack() {
        this.head = null;
        this.count = 0;
    }

    @Override
    public void add(Node node) {
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        count++;
    }

    @Override
    public void clear() {
        head = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Node find(int value) {
        Node curr = head;
        while (curr != null) {
            if (curr.val == value) {
                return curr;
            } else {
                curr = curr.next;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int value) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            if (curr.val == value) {
                break;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        if (curr == null) {
            return false;
        } else if (curr == head) {
            head = head.next;
        } else {
            prev.next = curr.next;
        }
        return true;
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque();
        int N = s.length();
        if (N % 2 != 0) {
            return false;
        }
        for (int i = 0; i < N; i++) {

            char c = s.charAt(i);

            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) return false;
                char p = stack.pop();
                if (p != '(') return false;
            } else if (c == '}') {
                if (stack.isEmpty()) return false;
                char p = stack.pop();
                if (p != '{') return false;
            } else if (c == ']') {
                if (stack.isEmpty()) return false;
                char p = stack.pop();
                if (p != '[') return false;
            }
        }
        if (stack.size() != 0) {
            return false;
        }
        return true;
    }
}
