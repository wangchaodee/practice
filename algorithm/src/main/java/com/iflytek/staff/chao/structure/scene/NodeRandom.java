package com.iflytek.staff.chao.structure.scene;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hamilton
 * @Description: 链表  增加了个指针 指向随机节点
 * @date Date : 2023年02月12日 10:17
 */
public class NodeRandom {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 138. 复制带随机指针的链表
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Map<Node , Node> map = new HashMap<>();
        Node ans = new Node(0);
        Node pre = ans;
        Node random = head ;
        // 构建链表
        while (head !=null){
            pre.next = new Node(head.val);
            pre = pre.next;
            pre.random = head.random;
            // 映射关系
            map.put(head , pre);
            head = head.next;
        }

        // random 指针
        pre = ans.next;
        while (random !=null){
            pre.random = map.get(pre.random);
            pre = pre.next ;
            random = random.next;
        }
        return ans.next ;
    }
}
