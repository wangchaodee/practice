package com.iflytek.staff.chao.structure.scene.loadbalance;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 哈希选择器
 */
public interface HashSelector extends Selector {

    Server getByRequest(Request request);

}
