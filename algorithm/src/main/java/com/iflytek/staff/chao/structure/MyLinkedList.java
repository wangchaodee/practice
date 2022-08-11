package com.iflytek.staff.chao.structure;


/**
 * @author : hamilton
 * @Description: 设计链表
 * @date Date : 2022年07月22日 13:10
 */
public class MyLinkedList {

    private ListNode head;

    public MyLinkedList() {
        head = new ListNode(-1);
    }

    public int get(int index) {
        if (index < 0) return -1;

        int idx = -1;
        ListNode cur = head;
        while (cur != null && idx < index) {
            idx++;
            cur = cur.next;
        }
        if (cur != null) {
            return cur.val;
        } else {
            return -1;
        }
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if (head.next == null) {
            head.next = node;
        } else {
            node.next = head.next;
            head.next = node;
        }
    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
            return;
        }

        int idx = -1;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null && idx < index) {
            idx++;
            pre = cur;
            cur = cur.next;
        }

        if (cur != null) {

            ListNode node = new ListNode(val, cur);
            pre.next = node;
        } else {
            if (idx == index) {
                ListNode node = new ListNode(val);
                pre.next = node;
            }
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0) return;

        int idx = -1;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null && idx < index) {
            idx++;
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            ListNode t = cur.next;
            pre.next = t;
        }
    }

    class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }
}
