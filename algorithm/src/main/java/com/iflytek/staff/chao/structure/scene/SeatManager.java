package com.iflytek.staff.chao.structure.scene;

import java.util.PriorityQueue;

/**
 * @author : wangchaodee
 * @Description:  1845. 座位预约管理系统
 * @date Date : 2023年02月14日 10:52
 */
public class SeatManager {

    private PriorityQueue<Integer> seats ;

    public SeatManager(int n) {
        seats = new PriorityQueue<>(n);
        for (int i = 1; i <= n; i++) {
            seats.offer(i);
        }
    }

    public int reserve() {
        return seats.poll();
    }

    public void unreserve(int seatNumber) {
        seats.offer(seatNumber);
    }
}
