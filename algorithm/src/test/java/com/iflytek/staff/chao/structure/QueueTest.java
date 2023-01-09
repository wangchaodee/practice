package com.iflytek.staff.chao.structure;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

    public static void main(String[] args) {
        // 1. Initialize a structure.
        Queue<Integer> q = new LinkedList();
        // 2. Get the first element - return null if structure is empty.
        System.out.println("The first element is: " + q.peek());
        // 3. Push new element.
        q.offer(5);
        q.offer(13);
        q.offer(8);
        q.offer(6);
        // 4. Pop an element.
        System.out.println(q.poll());
        // 5. Get the first element.
        System.out.println("The first element is: " + q.peek());
        // 7. Get the size of the structure.
        System.out.println("The size is: " + q.size());

    }
}