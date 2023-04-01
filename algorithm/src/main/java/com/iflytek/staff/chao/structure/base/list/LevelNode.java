package com.iflytek.staff.chao.structure.base.list;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class LevelNode {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };

    public Node flatten(Node head) {
        Node dummy = new Node() ;
        dummy.next  = head ;
        while (head !=null){
            Node temp = head.next ;
            if (head.child != null){
                Node  child = flatten(head.child);
                head.child =null;
                head.next = child ;
                child.prev = head;
                while (head.next !=null ){
                    head = head.next;
                }
            }
            temp.prev = head;
            head.next = temp ;
            head = temp ;
        }
        return dummy.next ;
    }


    /**
     * 剑指 Offer II 029. 排序的循环链表
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        if(head==null) {
            newNode.next = newNode;
            return newNode;
        }
        if(head.next ==head){
            head.next = newNode ;
            newNode.next = head ;
            return head ;
        }
        Node pre = head , next = head.next;
        while (next !=head){
            if(insertVal >=pre.val && insertVal<=next.val){
                break;
            }

            if(pre.val > next.val){
                if(insertVal>pre.val || insertVal< next.val){
                    break;
                }
            }
            pre = pre.next;
            next = next.next;
        }
        pre.next= newNode;
        newNode.next = next;
        return head ;
    }
}
