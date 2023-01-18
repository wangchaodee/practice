package com.iflytek.staff.chao.structure.base.list;

public class MyStack implements MyList {

    private ListNode head;
    private int count;

    public MyStack() {
        this.head = null;
        this.count = 0;
    }

    @Override
    public void add(ListNode ListNode) {
        if (head == null) {
            head = ListNode;
        } else {
            ListNode.next = head;
            head = ListNode;
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
    public ListNode find(int value) {
        ListNode curr = head;
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
        ListNode prev = null;
        ListNode curr = head;
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


}
