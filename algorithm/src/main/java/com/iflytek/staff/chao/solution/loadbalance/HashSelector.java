package com.iflytek.staff.chao.solution.loadbalance;

/**
 * @author : wangchaodee
 * @Description: 哈希选择器
 */
public interface HashSelector extends Selector {

    Server getByRequest(Request request);

}
