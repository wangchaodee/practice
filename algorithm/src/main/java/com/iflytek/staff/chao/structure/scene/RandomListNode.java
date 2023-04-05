package com.iflytek.staff.chao.structure.scene;

import com.iflytek.staff.chao.structure.base.list.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class RandomListNode {

    List<ListNode> list  ;
    Random random ;

    public RandomListNode(ListNode head) {
        list = new ArrayList<>();
        while (head != null){
            list.add(head) ;
            head = head.next;
        }
        random = new Random();
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size())).val ;
    }
}
