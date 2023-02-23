package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

/**
 * @author : wangchaodee
 * @Description: 固定窗口限流方式
 * @date Date : 2023年02月22日 21:00
 */
public class FixWindowRate implements RateLimitStrategy{
    @Override
    public boolean canHandle(Request request) {
        return false;
    }
}
