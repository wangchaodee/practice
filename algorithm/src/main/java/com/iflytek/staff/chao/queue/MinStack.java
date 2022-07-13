package com.iflytek.staff.chao.queue;

public class MinStack {

    private ListMinNode head ;

    public MinStack() {

    }


    public void push(int val) {
        int min=val ;
        if(head!=null) {
            min = Math.min(val,head.min);
        }
        ListMinNode cur = new ListMinNode(val,min ,head);
        head=cur;
    }

    public void pop() {
        ListMinNode next = head.next;
        head.next=null ;
        head= next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}

class ListMinNode {
    int val;
    int min ;
    ListMinNode next ;

     public  ListMinNode(int val ,int min ,ListMinNode next){
        this.val= val ;
        this.min= min ;
        this.next= next ;
    }
}