package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangchaodee
 * @Description: 漏桶限流算法方式
 */
@Slf4j
public class LeakyBucketRate implements RateLimitStrategy{

    private Stopwatch stopwatch;
    private AtomicInteger currentCount = new AtomicInteger(0);

    private Lock lock = new ReentrantLock();

    public LeakyBucketRate() {
        this(Stopwatch.createStarted());
    }

    protected LeakyBucketRate(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public boolean canHandle(Request request) {
        int cur = currentCount.incrementAndGet();
        return cur <= LIMIT ;
    }

    public TimerTask mockInnerTask(){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                        try {
                            if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                                int cur = currentCount.get() ;
                                int rate = LIMIT/DURATION ; // 消费速率
                                if(cur>=LIMIT){
                                    currentCount.set( LIMIT- rate);
                                }else {
                                    // 桶中消费   降为0 时不可消费
                                    currentCount.set(Math.max(cur- rate , 0));
                                }
                                stopwatch.reset();
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
                    throw  new RateLimitException("lock not get ,interrupted");
                }
            }
        };

        return timerTask;
    }

}
