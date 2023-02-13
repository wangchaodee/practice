package com.iflytek.staff.chao.structure.scene;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。
 * @date Date : 2022年12月28日 13:26
 */
public class StockPrice {

    private Map<Integer, Integer> priceMap;

    public StockPrice() {
        priceMap = new HashMap<>();
    }

    public void update(int timestamp, int price) {

    }

//    public int current() {
//
//    }
//
//    public int maximum() {
//
//    }
//
//    public int minimum() {
//
//    }
}
