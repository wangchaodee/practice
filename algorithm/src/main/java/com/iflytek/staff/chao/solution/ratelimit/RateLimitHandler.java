package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月22日 21:08
 */
public class RateLimitHandler {

    private RateLimitStrategy strategy ;

    /**
     * -1 代表 拒绝  0 代表 异常  ， 1 代表成功
     * @param request
     * @return
     */
    public int handle(Request request){
        strategy.canHandle(request);

        //re
        return 1 ;
    }
}
