package com.iflytek.staff.chao.solution.loadbalance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 负载均衡的模拟测试
 */
public class LoadBalanceTest {

    int serverNum = 5 ;
    int requestNum = 15000 ;


    /**
     *  服务器处理能力一样
     * @return
     */
    private List<Server> generateServerList(){
        List<Server> serverList = new ArrayList<>();
        for (int i = 0; i < serverNum; i++) {
            Server server = new Server(i);
            serverList.add(server);
        }
        return serverList ;
    }

    /**
     * 服务器处理能力不一样
     * @return
     */
    private List<Server> generateWeightServerList(){
        List<Server> serverList = new ArrayList<>();
        for (int i = 0; i < serverNum; i++) {
            Server server = new Server(i,100 * (i+1) );
            serverList.add(server);
        }
        return serverList ;
    }

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


    /**
     *  负载分发时依赖请求中的信息
     */
    private List<Request> generateRequestList(){
        Random random = new Random();
        // 执行请求
        List<Request> requestList = new ArrayList<>();
        Request base = new Request();
        base.setIp(2);
        for (int i = 1; i < requestNum; i++) {
            Request request = base.clone();
            // 模拟
            request.setArea(""+random.nextInt(i));
            requestList.add(request);
        }
        return requestList ;
    }

    private void execute(LoadBalance loadBalance , List<Request> requestList){
        for (int i = 0; i < requestList.size(); i++) {
            loadBalance.handleRequest(requestList.get(i));
        }
    }

    @Test
    public void testRandomLb(){

        LoadBalance loadBalance = new LoadBalance(new RandomSelector());
        System.out.println("SelectorName :" + loadBalance.getSelector().getClass().getSimpleName());
        loadBalance.registerServerList(generateServerList());
        // 执行请求
        execute(loadBalance,generateBlankRequestList());

        for(Server server : loadBalance.getServerList()){
            System.out.printf(" Server :%s , processed Request : %d \n " , server.hashCode() , server.getProcessed());
        }
    }

    @Test
    public void testRoundRobinLb(){

        LoadBalance loadBalance = new LoadBalance(new RoundRobinSelector());
        System.out.println("SelectorName :" + loadBalance.getSelector().getClass().getSimpleName());
        loadBalance.registerServerList(generateServerList());
        // 执行请求
        execute(loadBalance,generateBlankRequestList());

        for(Server server : loadBalance.getServerList()){
            System.out.printf(" Server :%s , processed Request : %d \n " , server.hashCode() , server.getProcessed());
        }
    }


    @Test
    public void testWeightRandomLb(){
        LoadBalance loadBalance =  new LoadBalance(new WeightRandomSelector());
        System.out.println("SelectorName :" + loadBalance.getSelector().getClass().getSimpleName());
        loadBalance.registerServerList(generateWeightServerList());
        // 执行请求
        execute(loadBalance,generateBlankRequestList());

        for(Server server : loadBalance.getServerList()){
            System.out.printf(" Server :%s  maxConnect : %d, processed Request : %d \n " ,
                    server.hashCode() ,server.getMaxConnect(), server.getProcessed());
        }
    }

    @Test
    public void testWeightRoundRobinLb(){

        LoadBalance loadBalance = new LoadBalance(new WeightRoundRobinSelector());
        System.out.println("SelectorName :" + loadBalance.getSelector().getClass().getSimpleName());
        loadBalance.registerServerList(generateWeightServerList());
        // 执行请求
        execute(loadBalance,generateBlankRequestList());

        for(Server server : loadBalance.getServerList()){
            System.out.printf(" Server :%s  maxConnect : %d, processed Request : %d \n " ,
                    server.hashCode() ,server.getMaxConnect(), server.getProcessed());
        }
    }

    @Test
    public void testMiniConnectLb(){

        LoadBalance loadBalance = new LoadBalance(new MiniConnectSelector());
        System.out.println("SelectorName :" + loadBalance.getSelector().getClass().getSimpleName());
        loadBalance.registerServerList(generateServerList());
        // 执行请求
        execute(loadBalance,generateBlankRequestList());

        for(Server server : loadBalance.getServerList()){
            System.out.printf(" Server :%s , processed Request : %d \n " , server.hashCode() , server.getProcessed());
        }
    }

    @Test
    public void testConsistentHashLb(){

        LoadBalance loadBalance = new LoadBalance(new ConsistentHashSelector());
        System.out.println("SelectorName :" + loadBalance.getSelector().getClass().getSimpleName());
        loadBalance.registerServerList(generateServerList());
        // 执行请求
        executeHash(loadBalance,generateRequestList());

        for(Server server : loadBalance.getServerList()){
            System.out.printf(" Server :%s , processed Request : %d \n " , server.hashCode() , server.getProcessed());
        }
    }

    private void executeHash(LoadBalance loadBalance , List<Request> requestList){
        for (int i = 0; i < requestList.size(); i++) {
            loadBalance.handleRequestByHash(requestList.get(i));
        }
    }

}