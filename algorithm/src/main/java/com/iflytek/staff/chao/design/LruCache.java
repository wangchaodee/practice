package com.iflytek.staff.chao.design;


import java.util.HashMap;
import java.util.Map;

class LrU {
    public static void main(String[] args) {

        //["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
        //[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]

        LRUCache cache = new LRUCache(3 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);

        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.put(4, 4);
        cache.get(4);       // 返回  1
        cache.get(3);       // 返回 -1 (未找到)
        cache.get(2);
        cache.get(1);
        cache.put(5, 5);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(2);
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
        cache.get(5);
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


class LRUCache {

    private Map<Integer, DLinkNode> indexs;
    private DLinkNode head;
    private DLinkNode tail;

    private int capacity;
    private int index;

    public LRUCache(int capacity) {
        indexs = new HashMap<>();
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
}
