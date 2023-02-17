package com.iflytek.staff.chao.structure.scene.loadbalance;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 轮询方式的负载均衡
 */
public class RoundRobinSelector implements Selector {

    private int idx ;
    private int count ;

    /**
     * 将服务器集群 注册到负载均衡器中
     *
     * @param serverList
     */
    RoundRobinSelector() {
        count =1;
        idx =0 ;
    }

    @Override
    public int generateIdx() {
        idx =( idx +1 ) % this.count ;
        return idx;
    }

    @Override
    public void registerServerList(List<Server> serverList) {
        count= serverList.size() ;
        idx =0 ;
    }

}
