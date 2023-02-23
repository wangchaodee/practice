package com.iflytek.staff.chao.solution.ratelimit;

import com.iflytek.staff.chao.solution.Request;
import lombok.Getter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@Getter
public class RateLimitHandler {

    private RateLimitStrategy strategy ;
    private Timer timer;

    public RateLimitHandler(RateLimitStrategy strategy) {
        this.strategy = strategy;
        this.timer = new Timer();
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

    public void schedule(){
        TimerTask task = strategy.mockInnerTask();
        if(task!=null) {
            timer.scheduleAtFixedRate(task, 0, strategy.mockTaskRate());
        }
    }
}
