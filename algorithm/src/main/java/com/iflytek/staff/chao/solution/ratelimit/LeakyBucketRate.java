package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

/**
 * @author : wangchaodee
 * @Description: 漏桶限流算法方式
 * @date Date : 2023年02月22日 21:02
 */
public class LeakyBucketRate implements RateLimitStrategy{

    @Override
    public boolean canHandle(Request request) {
        return false;
    }
}
