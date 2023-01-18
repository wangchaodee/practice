package com.iflytek.staff.chao.structure.base.list;

/**
 * 链表增删查
 */
public interface MyList {

    void add(ListNode ListNode);

    void clear();

    int size();

    ListNode find(int index);

    boolean delete(int index);

}
