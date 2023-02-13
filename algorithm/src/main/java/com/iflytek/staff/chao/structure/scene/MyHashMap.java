package com.iflytek.staff.chao.structure.scene;

/**
 * @author : wangchaodee
 * @Description: 模拟哈希表    固定数组+链表
 * @date Date : 2022年07月13日 08:52
 */
public class MyHashMap {

    HashNode[] array;
    int size;

    public MyHashMap() {
        size = 256;
        array = new HashNode[size];
    }

    public void put(int key, int value) {
        int index = key % size;
        HashNode node = array[index];
        while (node != null) {
            if (node.key == key) {
                node.value = value;
                return;
            } else {
                node = node.next;
            }
        }
        array[index] = new HashNode(key, value, array[index]);
    }

    public int get(int key) {
        int index = key % size;
        HashNode node = array[index];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            } else {
                node = node.next;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int index = key % size;
        HashNode prev = null;
        HashNode node = array[index];
        while (node != null) {
            if (node.key == key) {
                if (prev == null) {
                    array[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                break;
            } else {
                prev = node;
                node = node.next;
            }
        }
    }


    class HashNode {
        int key;
        int value;
        HashNode next;

        public HashNode(int key, int value, HashNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}


