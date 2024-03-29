package com.iflytek.staff.chao.solution.loadbalance;

import com.iflytek.staff.chao.solution.Server;

import java.util.List;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 随机方式的负载均衡
 */
public class RandomSelector implements Selector {

    private Random random;

    private int count ;

    public RandomSelector() {
        this.random = new Random();
        this.count =1 ;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public int generateIdx() {
        return  random.nextInt(this.count);
    }

    @Override
    public void registerServerList(List<Server> serverList) {
        count= serverList.size() ;
    }

}
