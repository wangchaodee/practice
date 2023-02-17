package com.iflytek.staff.chao.structure.scene.loadbalance;

import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 加权随机方式的负载均衡   力扣 528 加权随机
 */
public class WeightRandomSelector extends WeightSelector {


    private Random random;

    public WeightRandomSelector() {
        this.random = new Random();
    }

    /**
     * 用随机方式生成cur 的值
     * @return
     */
    public int generateCur() {
        cur = random.nextInt(getTotal());
        return cur;
    }

}
