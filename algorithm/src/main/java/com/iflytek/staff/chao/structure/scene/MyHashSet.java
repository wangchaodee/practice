package com.iflytek.staff.chao.structure.scene;

/**
 * @author : hamilton
 * @Description: 705. 设计哈希集合
 * @date Date : 2023年02月06日 14:41
 */
public class MyHashSet {

    // 定长数组
/*    boolean[] set;
    public MyHashSet() {
        set = new boolean[1000001];
    }

    public void add(int key) {
        set[key] = true ;
    }

    public void remove(int key) {
        set[key] = false ;
    }

    public boolean contains(int key) {
        return set[key] ;
    }*/

    HashNode[] array;
    int size;

    public MyHashSet() {
        size = 256;
        array = new HashNode[size];
    }

    public void add(int key) {
        int index = key % size;
        HashNode node = array[index];
        while (node != null) {
            if (node.key == key) {
                return;
            } else {
                node = node.next;
            }
        }
        array[index] = new HashNode(key, array[index]);
    }

    public boolean contains(int key) {
        int index = key % size;
        HashNode node = array[index];
        while (node != null) {
            if (node.key == key) {
                return true;
            } else {
                node = node.next;
            }
        }
        return false;
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
        HashNode next;

        public HashNode(int key, HashNode next) {
            this.key = key;
            this.next = next;
        }
    }
}
