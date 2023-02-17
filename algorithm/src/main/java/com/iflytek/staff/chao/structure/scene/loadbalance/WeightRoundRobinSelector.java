package com.iflytek.staff.chao.structure.scene.loadbalance;

/**
 * @author : wangchaodee
 * @Description: 加权轮询方式的负载均衡
 */
public class WeightRoundRobinSelector extends WeightSelector {


    /**
     * 用轮询方式生成 当前cur 即可
     * @return
     */
    public int generateCur(){
        cur =( cur +1 ) % this.getTotal() ;
        return cur;
    }


}
