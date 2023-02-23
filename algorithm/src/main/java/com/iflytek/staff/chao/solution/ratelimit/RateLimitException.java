package com.iflytek.staff.chao.solution.ratelimit;

/**
 * @author : wangchaodee
 * @Description: 定义异常
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException() {
    }

    public RateLimitException(String message) {
        super(message);
    }
}
