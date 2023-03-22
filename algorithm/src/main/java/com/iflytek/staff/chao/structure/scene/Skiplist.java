package com.iflytek.staff.chao.structure.scene;

import java.util.Arrays;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 跳表的实现 1206
 * @date Date : 2023年01月09日 11:17
 */
public class Skiplist {

     static final int MAX_LEVEL =32 ;
     static final double P_FACTOR = 0.25 ;
    private Node head ;
    private int level ;
    private Random random ;

    public Skiplist() {
        this.head = new Node(-1, MAX_LEVEL);
        this.level = 0 ;
        this.random = new Random();
    }

    public boolean search(int target) {
        Node curr = this.head;
        for (int i = level-1; i >=0 ; i--) {
            while (curr.forward[i] !=null && curr.forward[i].val < target){
                curr = curr.forward[i];
            }
        }
        curr = curr.forward[0];
        if(curr!=null && curr.val == target){
            return true ;
        }
        return false ;
    }

    public void add(int num) {
        Node[] update = new Node[MAX_LEVEL] ;
        Arrays.fill(update, head);
        Node curr = this.head ;
        for (int i = level-1; i >=0 ; i--) {
            while (curr.forward[i] !=null && curr.forward[i].val < num){
                curr = curr.forward[i];
            }
            update[i] = curr;
        }
        int lv = randomLevel();
        level = Math.max(level,lv);
        Node newNode = new Node(num,lv);
        for (int i = 0; i < lv; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public boolean erase(int num) {
        Node[] update = new Node[MAX_LEVEL];
        Node curr = this.head ;
        for (int i = level-1; i >=0 ; i--) {
            while (curr.forward[i] !=null && curr.forward[i].val < num){
                curr = curr.forward[i];
            }
            update[i] = curr;
        }
        curr = curr.forward[0];
        if(curr==null || curr.val !=num){
            return false;
        }
        for (int i = 0; i < level; i++) {
            if(update[i].forward[i] != curr){
                break;
            }
            update[i].forward[i] = curr.forward[i];
        }
        while (level>1 && head.forward[level-1]==null){
            level--;
        }
        return true ;
    }

    private int randomLevel(){
        int lv = 1;
        while (random.nextDouble() < P_FACTOR && lv <MAX_LEVEL){
            lv++;
        }
        return lv;
    }

    class Node {
        int val ;
        Node[] forward ;

        public Node(int val , int maxLevel){
            this.val = val ;
            this.forward = new Node[maxLevel];
        }
    }

}
