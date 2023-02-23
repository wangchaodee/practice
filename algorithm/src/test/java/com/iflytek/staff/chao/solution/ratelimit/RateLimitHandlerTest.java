package com.iflytek.staff.chao.solution.ratelimit;

import com.google.common.base.Stopwatch;
import com.iflytek.staff.chao.solution.Request;
import com.iflytek.staff.chao.solution.loadbalance.LoadBalance;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * @author : wangchaodee
 * @Description: 限流的模拟测试
 */
public class RateLimitHandlerTest {


    int threadNum = 3 ;
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

    private List<Callable<Map<Integer ,Integer>>> generateTasks(RateLimitHandler rateLimitHandler , List<Request> requestList){
        List<Callable<Map<Integer ,Integer>>> tasks = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            List<Request> copyList = Collections.unmodifiableList(requestList) ;
            int sleepTime = 10*(i+1);
            Callable<Map<Integer ,Integer>> task = new Callable() {
                @Override
                public Object call() throws Exception {
                    Map<Integer ,Integer> statusCount = new HashMap<>() ;
                    for (int i = 0; i < copyList.size(); i++) {
                        int key = rateLimitHandler.handle(copyList.get(i)) ;
                        statusCount.put(key , statusCount.getOrDefault(key,0)+1 );
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return statusCount ;
                }
            };
            tasks.add(task);
        }
        return tasks;
    }

    private Map<Integer ,Integer> execute(RateLimitHandler rateLimitHandler , List<Request> requestList){


        ExecutorService executor= Executors.newFixedThreadPool(5);
        List<Callable<Map<Integer ,Integer>>> tasks = generateTasks(rateLimitHandler,requestList);

        for (int i = 0; i <threadNum ; i++) {
            executor.submit(tasks.get(i));
        }

        Map<Integer ,Integer> statusCount = new HashMap<>() ;
        try {
            for (int i = 0; i < threadNum; i++) {
                Map<Integer, Integer> taskRes = tasks.get(i).call();
                for (Map.Entry<Integer, Integer> res : taskRes.entrySet()) {
                    statusCount.put(res.getKey(), statusCount.getOrDefault(res.getKey(),0) + res.getValue());
                }
            }

        }catch (Exception e){
            System.out.println("get result error ");
        }

        return statusCount ;
    }

    @Test
    public void testFixRateLimit() {
        RateLimitHandler rateLimitHandler = new RateLimitHandler(new FixWindowRate());
        System.out.println("rate limit Strategy : " + rateLimitHandler.getStrategy().getClass().getSimpleName()) ;
        Stopwatch stopwatch = Stopwatch.createStarted() ;
        rateLimitHandler.schedule();
        Map<Integer ,Integer> statusCount =  execute(rateLimitHandler,generateBlankRequestList());
        System.out.println("time : " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        for (Map.Entry<Integer,Integer> res : statusCount.entrySet()) {
            System.out.printf("key %d , nums : %d \n ", res.getKey() ,res.getValue());
        }
    }

    @Test
    public void testSlidingWindowRateLimit() {
        RateLimitHandler rateLimitHandler = new RateLimitHandler(new SlidingWindowRate());
        System.out.println("rate limit Strategy : " + rateLimitHandler.getStrategy().getClass().getSimpleName()) ;
        Stopwatch stopwatch = Stopwatch.createStarted() ;
        rateLimitHandler.schedule();
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
        rateLimitHandler.schedule();
        Map<Integer ,Integer> statusCount =  execute(rateLimitHandler,generateBlankRequestList());
        System.out.println("time : " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        for (Map.Entry<Integer,Integer> res : statusCount.entrySet()) {
            System.out.printf("key %d , nums : %d \n ", res.getKey() ,res.getValue());
        }
    }

    @Test
    public void testLeakyBucketRateLimit() {
        RateLimitHandler rateLimitHandler = new RateLimitHandler(new LeakyBucketRate());
        System.out.println("rate limit Strategy : " + rateLimitHandler.getStrategy().getClass().getSimpleName()) ;
        Stopwatch stopwatch = Stopwatch.createStarted() ;
        rateLimitHandler.schedule();
        Map<Integer ,Integer> statusCount =  execute(rateLimitHandler,generateBlankRequestList());
        System.out.println("time : " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        for (Map.Entry<Integer,Integer> res : statusCount.entrySet()) {
            System.out.printf("key %d , nums : %d \n ", res.getKey() ,res.getValue());
        }
    }
}