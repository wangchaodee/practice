package com.iflytek.staff.chao.structure.scene;

/**
 * 622. 设计循环队列
 */
public class MyCircularQueue {


    private int[] array;
    private int head;
    private int tail;


    public MyCircularQueue(int k) {
        array = new int[k + 1];
        head = 0;
        tail = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        array[tail] = value;
        tail = ++tail % array.length;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = ++head % array.length;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return array[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return array[(tail - 1 + array.length) % array.length];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }
}
