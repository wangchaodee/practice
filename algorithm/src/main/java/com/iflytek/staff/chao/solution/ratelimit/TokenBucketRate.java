package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangchaodee
 * @Description: 令牌桶限流算法 模拟
 */
@Slf4j
public class TokenBucketRate implements RateLimitStrategy{

    private Stopwatch stopwatch;
    private AtomicLong lastHandled;
    private AtomicInteger tokenAmount = new AtomicInteger(0);

    private Lock lock = new ReentrantLock();

    public TokenBucketRate() {
        this(Stopwatch.createStarted());
    }

    protected TokenBucketRate(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
        lastHandled = new AtomicLong(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    public boolean canHandle(Request request) {
        int cur = tokenAmount.decrementAndGet();
        return cur>=0;
    }

    public TimerTask mockInnerTask(){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                        try {
                            if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {

                                int cur = tokenAmount.get();
                                int rate = LIMIT/DURATION;
                                if(cur<=0){
                                    // 令牌桶空了时  直接设置令牌
                                    tokenAmount.set(rate);
                                }else {
                                    // // 令牌桶非空时   累加令牌  但不能超上限
                                    tokenAmount.set(Math.min(LIMIT,cur + rate));
                                }
                                stopwatch.reset();
                            }
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        log.info(" canHandle() can not get lock  by lock timeout %s ms ", TRY_LOCK_TIMEOUT);
                        throw new RateLimitException("lock not get,timeout");
                    }
                } catch (InterruptedException e) {
                    log.error(" canHandle() is interrupted bu lock timeout ");
                    throw new RateLimitException("lock not get ,interrupted");
                }
            }
        };

        return timerTask;
    }
}
