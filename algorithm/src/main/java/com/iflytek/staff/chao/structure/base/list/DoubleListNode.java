package com.iflytek.staff.chao.structure.base.list;

/**
 * @author : wangchaodee
 * @Description: 链表 算法
 * @date Date : 2022年06月23日 下午3:49
 */
public class DoubleListNode  {

    public int val;
    public DoubleListNode next;
    public DoubleListNode prev;

//    DoubleListNode() {
//    }

    public DoubleListNode(int val) {
        this.val = val;
    }

    public DoubleListNode(int val, DoubleListNode next, DoubleListNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}
