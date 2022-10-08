package com.iflytek.staff.chao.design;

/**
 * @author : hamilton
 * @Description: 设计停车系统
 * @date Date : 2022年07月21日 08:16
 */
public class ParkingSystem {

    private int[] parkingLot;


    /**
     * 对应车型 carType  1,2,3
     *
     * @param big    1
     * @param medium 2
     * @param small  3
     */
    public ParkingSystem(int big, int medium, int small) {
        parkingLot = new int[4];
        parkingLot[1] = big;
        parkingLot[2] = medium;
        parkingLot[3] = small;
    }

    public boolean addCar(int carType) {
        // 减掉之后大于0 则
        if (--parkingLot[carType] >= 0) {
            return true;
        } else {
            //还原车位的计数
            ++parkingLot[carType];
            return false;
        }
    }
}
