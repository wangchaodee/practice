package com.iflytek.staff.chao.structure.scene.cache;


import java.util.HashMap;
import java.util.Map;


/**
 * 146. LRU 缓存,  哈希链表
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 */
public class LRUCache  implements Cache {

    private Map<Integer, DLinkNode> cache;
    // 维护一个队列 来表示数据近期被使用的顺序
    private DLinkList queue;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        queue = new DLinkList();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key).value;
    }

    public void put(int key, int value) {

        if(cache.containsKey(key)){
            deleteKey(key);
            addRecently(key,value);
            return;
        }

        if (capacity == queue.size()) {
            //删除最近最少使用
           removeLeastRecently();
        }

        addRecently(key,value);
    }

    private void makeRecently(int key){
        DLinkNode node = cache.get(key);

        // 将节点移动到队列尾部代表 最近被使用
        queue.removeNode(node);
        queue.addToTail(node);
    }

    private void addRecently(int key ,int value){
        DLinkNode node = new DLinkNode(key,value);
        queue.addToTail(node);
        cache.put(key,node);
    }

    private void deleteKey(int key){
        DLinkNode node = cache.get(key);
        queue.removeNode(node);
        cache.remove(key);
    }

    private void removeLeastRecently(){
        DLinkNode node = queue.removeFirst();
        if(node !=null) cache.remove(node.key);
    }


    class DLinkNode {
        public int key;
        public int value;
        public DLinkNode left;
        public DLinkNode right;

        DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DLinkList {
        private DLinkNode head , tail ;
        private int size;

        public DLinkList(){
            this.head = new DLinkNode(0,0);
            this.tail = new DLinkNode(0,0);
            head.right = tail;
            tail.left = head;
            size=0;
        }

        public void addToTail(DLinkNode node) {
            //尾部插入
            node.left = tail.left;
            node.right = tail;

            tail.left.right = node;
            tail.left = node;
            size++;
        }

        public DLinkNode removeFirst() {
            if(head.right == tail ) return null ;

            DLinkNode node = head.right;
            removeNode(node);
            return node;
        }

        private void removeNode(DLinkNode node) {
            // 去除
            node.left.right = node.right;
            node.right.left = node.left;
            size--;
        }

        public int size(){
            return size ;
        }
    }
}
