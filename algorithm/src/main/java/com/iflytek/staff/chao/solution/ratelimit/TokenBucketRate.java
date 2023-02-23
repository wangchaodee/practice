package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangchaodee
 * @Description: 令牌桶限流算法
 * @date Date : 2023年02月22日 21:03
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

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {

                    long cur = stopwatch.elapsed(TimeUnit.MILLISECONDS);
                    long interval = cur - lastHandled.get();
                    int expect = (int) (interval * (LIMIT / TimeUnit.SECONDS.toMillis(DURATION))) + tokenAmount.get();
                    if (expect > LIMIT) {
                        tokenAmount.set(LIMIT);
                    } else {
                        tokenAmount.set(expect);
                    }
                    if (tokenAmount.get() <= 0) {
                        return false;
                    }

                    tokenAmount.decrementAndGet();
                    lastHandled.set(cur);
                    return true;
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
}
