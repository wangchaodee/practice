package com.iflytek.staff.chao.structure.scene;


import com.iflytek.staff.chao.structure.base.list.DoubleListNode;
import com.iflytek.staff.chao.structure.base.list.ListNode;

/**
 * @author : wangchaodee
 * @Description: 707 设计链表
 * @date Date : 2022年07月22日 13:10
 */
public class MyLinkedDoubleList {

    private DoubleListNode head;

    private DoubleListNode tail ;

    private int size ;


    public MyLinkedDoubleList() {
        size = 0;
    }

    public int get(int index) {
        if(!check(index)) return -1;

        return  node(index).val;
    }

    private DoubleListNode node(int index){
        if(index< (size>>1) ){
            DoubleListNode x = head;
            for (int i = 0; i < index; i++) {
                x =  x.next;
            }
            return x ;
        }else {
            DoubleListNode x = tail;
            for (int i = size-1; i >index ; i--) {
                x = x.prev ;
            }
            return x;
        }
    }

    private boolean check(int index){
        if (index < 0  || index>=size) return false;
        return true;
    }

    public void addAtHead(int val) {
        DoubleListNode h =head;
        DoubleListNode newNode = new DoubleListNode(val,h,null);
        if (h == null) {
            tail = newNode;
        } else {
            h.prev = newNode;
        }
        head= newNode;
        size++;
    }

    public void addAtTail(int val) {
        DoubleListNode t =tail;
        DoubleListNode newNode = new DoubleListNode(val,null,t);
        if (t == null) {
            head = newNode;
        } else {
            t.next = newNode;
        }
        tail= newNode;
        size++;
    }

    /**
     * 在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，
     * 则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
     * 如果index小于0，则在头部插入节点。
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        if(index>size) return;
        if(index<=0){
            addAtHead(val);
        }else if(index == size){
            addAtTail(val);
        }else {
            DoubleListNode next = node(index);
            DoubleListNode newNode = new DoubleListNode(val,next,next.prev);
            next.prev.next = newNode ;
            next.prev = newNode;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (!check(index)) return;
        DoubleListNode node = node(index);
        DoubleListNode next = node.next;
        DoubleListNode prev = node.prev;
        if(next !=null){
            node.next=null;
            next.prev = prev;
        }else {
            tail = prev;
        }
        if(prev !=null) {
            node.prev = null;
            prev.next = next;
        }else {
            head = next;
        }
        size--;
    }


}
