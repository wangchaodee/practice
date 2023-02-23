package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

/**
 * @author : wangchaodee
 * @Description: 滑动窗口限流算法方式
 * @date Date : 2023年02月22日 21:01
 */
public class SlidingWindowRate implements RateLimitStrategy{
    @Override
    public boolean canHandle(Request request) {
        return false;
    }
}
