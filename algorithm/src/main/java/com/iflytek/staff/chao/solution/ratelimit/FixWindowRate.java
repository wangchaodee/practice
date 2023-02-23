package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangchaodee
 * @Description: 固定窗口限流方式
 * <p>
 * 参考 ：https://time.geekbang.org/column/article/243961
 */
@Slf4j
public class FixWindowRate implements RateLimitStrategy {


    private Stopwatch stopwatch;
    private AtomicInteger currentCount = new AtomicInteger(0);

    private Lock lock = new ReentrantLock();

    public FixWindowRate() {
        this(Stopwatch.createStarted());
    }

    protected FixWindowRate(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public boolean canHandle(Request request) {
        int cur = currentCount.incrementAndGet();
        if (cur <= LIMIT) return true;

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(DURATION)) {
                        currentCount.set(0);
                        stopwatch.reset();
                    }
                    cur = currentCount.incrementAndGet();
                    return cur <= LIMIT;
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
}
