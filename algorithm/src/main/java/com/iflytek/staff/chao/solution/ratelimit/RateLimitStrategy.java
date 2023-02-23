package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public interface RateLimitStrategy {

    long TRY_LOCK_TIMEOUT = 20L; // 20ms.
    int DURATION = 5; // 5s

    // 限流
    int LIMIT = 100;

    boolean canHandle(Request request) throws RateLimitException;


}
