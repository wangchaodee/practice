package com.iflytek.staff.chao.structure;

public class MyCircularQueue {


    private int[] array;
    private int head;
    private int tail;
//    private int size;
//    public int count;

    public MyCircularQueue(int k) {
        array = new int[k + 1];
//        size = k+1;
//        count = 0;
        head = 0;
        tail = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        array[tail] = value;
        tail = ++tail % array.length;
//        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = ++head % array.length;
//        count--;
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
