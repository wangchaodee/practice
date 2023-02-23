package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangchaodee
 * @Description: 滑动窗口限流算法方式
 */
@Slf4j
public class SlidingWindowRate implements RateLimitStrategy{

    Deque<Long> deque ;

    Stopwatch stopwatch ;

    private Lock lock = new ReentrantLock();


    public SlidingWindowRate() {
        this(new ArrayDeque<Long>(LIMIT) ,Stopwatch.createStarted());
    }

    public SlidingWindowRate(Deque deque ,Stopwatch stopwatch) {
        this.deque = deque;
        this.stopwatch = stopwatch;
    }

    /**
     * 用的ringbuffer 方式  轮转的滑动窗口
     * 单点 每次都锁  消耗比较大
     * @param request
     * @return
     */
    @Override
    public boolean canHandle(Request request) {
        long now = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if(deque.size()< LIMIT){
                        deque.addLast(now);
                        return true ;
                    }else {
                        long interval  = now -  deque.getFirst();
                        // 大于限定时间 则表明限定时间内的 请求量小于阈值
                        if(interval >= TimeUnit.SECONDS.toMillis(DURATION)){
                            deque.removeFirst();
                            deque.addLast(now);
                            return true ;
                        }
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                log.info(" canHandle() can not get lock  by lock timeout %s ms ", TRY_LOCK_TIMEOUT);
                throw  new RateLimitException("lock not get,timeout");
            }
        } catch (InterruptedException e) {
            log.error(" canHandle() is interrupted bu lock timeout ");
            throw  new RateLimitException("lock not get");
        }

        return false;
    }

    @Override
    public TimerTask mockInnerTask() {
        // 暂时不需要
        return null;
    }
}
