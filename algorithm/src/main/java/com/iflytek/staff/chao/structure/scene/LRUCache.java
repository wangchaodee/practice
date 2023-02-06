package com.iflytek.staff.chao.structure.scene;


import java.util.HashMap;
import java.util.Map;


/**
 * 146. LRU 缓存
 */
public class LRUCache {

    private Map<Integer, DLinkNode> indexs;
    private DLinkNode head;
    private DLinkNode tail;

    private int capacity;
    private int index;

    public LRUCache(int capacity) {
        indexs = new HashMap<>(capacity);
        this.capacity = capacity;
        this.index = 0;
        this.head = new DLinkNode();
        this.tail = new DLinkNode();
        head.right = tail;
        tail.left = head;
    }

    public int get(int key) {
        DLinkNode node = indexs.get(key);

        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(DLinkNode node) {
        // 去除
        removeNode(node);
        // 移到头部
        addToHead(node);

    }

    private void removeNode(DLinkNode node) {
        // 去除
        node.left.right = node.right;
        node.right.left = node.left;
    }

    private void addToHead(DLinkNode node) {
        // 移到头部
        head.right.left = node;
        node.right = head.right;
        node.left = head;
        head.right = node;
    }

    private DLinkNode deleteTail() {
        DLinkNode node = tail.left;
        removeNode(node);
        return node;
    }

    public void put(int key, int value) {
        DLinkNode node = indexs.get(key);

        if (node == null) {
            node = new DLinkNode(key, value);
            if (index == capacity) {
                //删除尾部
                DLinkNode del = deleteTail();
                indexs.remove(del.key);
                index--;
            }
            addToHead(node);
            indexs.put(key, node);
            index++;
        } else {
            //更新
            node.value = value;
            moveToHead(node);
        }
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

        DLinkNode() {

        }
    }
}
