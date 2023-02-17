package com.iflytek.staff.chao.structure.scene.loadbalance;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 选择器
 */
public interface Selector {

    /**
     *  返回按特定策略计算出的服务器ID
     * @return
     */
    int generateIdx() ;

    void registerServerList(List<Server> serverList);

}
