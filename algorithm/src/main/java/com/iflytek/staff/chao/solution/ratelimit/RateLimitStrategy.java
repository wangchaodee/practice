package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

import java.util.TimerTask;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public interface RateLimitStrategy {

    long TRY_LOCK_TIMEOUT = 20L; // 20ms.
    int DURATION = 5; // 5s

    // 限流
    int LIMIT = 100;

    /**
     * 判断是否限流
     * @param request
     * @return
     * @throws RateLimitException
     */
    boolean canHandle(Request request) throws RateLimitException;


    /**
     * 模拟内部的处理  可选
     * 比如移动时间窗口 或 生成令牌  或 消费桶中任务
     *
     * @return
     */
    TimerTask mockInnerTask();
}
