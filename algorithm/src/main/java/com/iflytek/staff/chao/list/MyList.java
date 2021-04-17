package com.iflytek.staff.chao.list;

/**
 * 链表增删查
 */
public interface MyList {

    void add(Node node);

    void clear();

    int size();

    Node find(int index);

    boolean delete(int index);

}
