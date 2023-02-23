package com.iflytek.staff.chao.solution.loadbalance;

import com.iflytek.staff.chao.solution.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 最小连接方式的负载均衡
 */
public class MiniConnectSelector implements Selector {

    private Random random;

    // 用优先队列维护服务器链接数量的排序
    private PriorityQueue<Server> queue ;
    public MiniConnectSelector() {
        this.random = new Random();
        this.queue = new PriorityQueue<>((a,b)-> a.getCurrentConnect()- b.getCurrentConnect());
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public int generateIdx() {
        Server server = queue.peek();
        // 模拟间隔的刷新 服务器的当前连接数量
        if(server.getCurrentConnect()==server.getMaxConnect()){
            mockRefreshConnect();
        }
        // 取得最少连接 ，加1后 放回排序
        server = queue.poll();
        server.increaseCurrentConnect();
        queue.offer(server);
        return server.getId();
    }

    @Override
    public void registerServerList(List<Server> serverList) {
        refreshQueue(serverList);
    }

    private void refreshQueue(List<Server> serverList){
        queue.clear();
        for (Server server : serverList){
            server.refreshCurrentConnect(random);
            queue.offer(server);
        }
    }

    private void mockRefreshConnect(){
        List<Server> serverList = new ArrayList<>();
        while (!queue.isEmpty()){
            serverList.add(queue.poll());
        }
        refreshQueue(serverList);
    }

}
