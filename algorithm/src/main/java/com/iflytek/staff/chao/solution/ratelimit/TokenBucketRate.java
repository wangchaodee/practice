package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

/**
 * @author : wangchaodee
 * @Description: 令牌桶限流算法
 * @date Date : 2023年02月22日 21:03
 */
public class TokenBucketRate implements RateLimitStrategy{
    @Override
    public boolean canHandle(Request request) {
        return false;
    }
}
