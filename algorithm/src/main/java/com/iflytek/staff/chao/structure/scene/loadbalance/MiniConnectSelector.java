package com.iflytek.staff.chao.structure.scene.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 最小连接方式的负载均衡
 */
public class MiniConnectSelector implements Selector {

    private Random random;

    private int count ;

    public MiniConnectSelector() {
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
