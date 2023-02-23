package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;

import java.util.TimerTask;
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
        return cur <= LIMIT;
    }


    public TimerTask mockInnerTask(){

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                try {
                    System.out.printf("TimerTask mockInnerTask : %s \n", this.getClass().getSimpleName() );
                    if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println( "currentCount: " + currentCount.get());
                            // 间隔着才会重置
                            if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(DURATION)) {
                                currentCount.set(0);
                                stopwatch.reset();
                                stopwatch.start();
                            }
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        System.out.printf(" canHandle() can not get lock  by lock timeout %s ms \n", TRY_LOCK_TIMEOUT);
                        throw  new RateLimitException("lock not get,timeout");
                    }
                } catch (InterruptedException e) {
                    System.out.println(" canHandle() is interrupted bu lock timeout ");
                    throw  new RateLimitException("lock not get ,interrupted");
                }
            }
        };

        return timerTask;
    }

    @Override
    public long mockTaskRate() {
        return TimeUnit.SECONDS.toMillis(DURATION);
    }
}
