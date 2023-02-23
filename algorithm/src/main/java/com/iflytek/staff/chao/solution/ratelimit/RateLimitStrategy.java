package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月22日 21:15
 */
public interface RateLimitStrategy {

    boolean canHandle(Request request);
}
