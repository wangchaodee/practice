package com.iflytek.staff.chao.structure.base.list;

/**
 * @author : hamilton
 * @Description: 链表 算法
 * @date Date : 2022年06月23日 下午3:49
 */
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


}
