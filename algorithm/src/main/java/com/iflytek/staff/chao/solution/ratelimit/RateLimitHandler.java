package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;
import lombok.Getter;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月22日 21:08
 */
@Getter
public class RateLimitHandler {

    private RateLimitStrategy strategy ;

    public RateLimitHandler(RateLimitStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * -1 代表 拒绝  0 代表 异常  ， 1 代表成功
     * @param request
     * @return
     */
    public int handle(Request request){
        try {
             if(strategy.canHandle(request)){
                 return  1;
             }else {
                 return -1;
             }
        }catch (RateLimitException e){
            return  0 ;
        }
    }
}
