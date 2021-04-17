package com.iflytek.staff.chao.list;

import java.util.Stack;

public class MyStack  implements MyList {

    private Node head;
    private int count ;

    public MyStack() {
        this.head =null;
        this.count = 0;
    }

    @Override
    public void add(Node node) {
        if(head==null){
            head=node;
        }else {
            node.next=head;
            head =node ;
        }
        count++ ;
    }

    @Override
    public void clear() {
        head=null;
        count=0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Node find(int value) {
        Node curr = head;
        while (curr!=null){
            if(curr.val==value){
                return curr;
            }else {
                curr =curr.next;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int value) {
        Node prev = null;
        Node curr = head;
        while (curr!=null){
            if(curr.val==value){
                break;
            }else {
                prev = curr;
                curr =curr.next;
            }
        }
        if(curr==null){
            return false;
        }else if(curr==head){
            head = head.next;
        }else {
            prev.next=curr.next;
        }
        return true;
    }
}
