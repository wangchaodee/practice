package com.iflytek.staff.chao.queue;

public class MyCircularQueue {


    private int[] array;
    private int head;
    private int tail;
    private int size;
    public int count;

    public MyCircularQueue(int k) {
        array = new int[k];
        size = k;
        count = 0;
        head = 0;
        tail = -1;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        tail = ++tail % size;
        array[tail] = value;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = ++head % size;
        count--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return array[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return array[tail];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }
}
