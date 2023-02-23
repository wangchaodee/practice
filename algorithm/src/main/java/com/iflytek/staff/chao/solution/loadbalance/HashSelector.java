package com.iflytek.staff.chao.solution.loadbalance;

import com.iflytek.staff.chao.solution.Request;
import com.iflytek.staff.chao.solution.Server;

/**
 * @author : wangchaodee
 * @Description: 哈希选择器
 */
public interface HashSelector extends Selector {

    Server getByRequest(Request request);

}
