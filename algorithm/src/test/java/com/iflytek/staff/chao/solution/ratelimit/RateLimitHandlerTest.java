package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;
import com.iflytek.staff.chao.solution.loadbalance.LoadBalance;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author : wangchaodee
 * @Description: 限流的模拟测试
 */
public class RateLimitHandlerTest {


    int threadNum = 5 ;
    int requestNum = 200 ;


    /**
     *  产生通用的空白请求 ，负载分发时不依赖请求中的信息
     */
    private List<Request> generateBlankRequestList(){
        // 执行请求
        List<Request> requestList = new ArrayList<>();
        Request base = new Request();
        for (int i = 0; i < requestNum; i++) {
            Request request = base.clone();
            requestList.add(request);
        }
        return requestList ;
    }


    private Map<Integer ,Integer> execute(RateLimitHandler rateLimitHandler , List<Request> requestList){

        Map<Integer ,Integer> statusCount = new HashMap<>() ;
        for (int i = 0; i < requestList.size(); i++) {
            int key = rateLimitHandler.handle(requestList.get(i)) ;
            statusCount.put(key , statusCount.getOrDefault(key,0)+1 );
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return statusCount ;
    }

    @Test
    public void testFixRateLimit() {
        RateLimitHandler rateLimitHandler = new RateLimitHandler(new FixWindowRate());
        System.out.println("rate limit Strategy : " + rateLimitHandler.getStrategy().getClass().getSimpleName()) ;

        Map<Integer ,Integer> statusCount =  execute(rateLimitHandler,generateBlankRequestList());
        for (Map.Entry<Integer,Integer> res : statusCount.entrySet()) {
            System.out.printf("key %d , nums : %d \n ", res.getKey() ,res.getValue());
        }
    }

    @Test
    public void testSlidingWindowRateLimit() {
        RateLimitHandler rateLimitHandler = new RateLimitHandler(new SlidingWindowRate());
        System.out.println("rate limit Strategy : " + rateLimitHandler.getStrategy().getClass().getSimpleName()) ;
        Stopwatch stopwatch = Stopwatch.createStarted() ;
        Map<Integer ,Integer> statusCount =  execute(rateLimitHandler,generateBlankRequestList());
        System.out.println("time : " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        for (Map.Entry<Integer,Integer> res : statusCount.entrySet()) {
            System.out.printf("key %d , nums : %d \n ", res.getKey() ,res.getValue());
        }
    }

    @Test
    public void testTokenBucketRateLimit() {
        RateLimitHandler rateLimitHandler = new RateLimitHandler(new TokenBucketRate());
        System.out.println("rate limit Strategy : " + rateLimitHandler.getStrategy().getClass().getSimpleName()) ;
        Stopwatch stopwatch = Stopwatch.createStarted() ;
        Map<Integer ,Integer> statusCount =  execute(rateLimitHandler,generateBlankRequestList());
        System.out.println("time : " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        for (Map.Entry<Integer,Integer> res : statusCount.entrySet()) {
            System.out.printf("key %d , nums : %d \n ", res.getKey() ,res.getValue());
        }
    }
}