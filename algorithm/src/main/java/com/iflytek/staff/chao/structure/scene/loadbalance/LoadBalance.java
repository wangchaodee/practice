package com.iflytek.staff.chao.structure.scene.loadbalance;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 基础的负载均衡器
 * 1、初始化时就注册选择器（设定负载均衡的算法方式）
 * 2、然后注册服务器列表
 * 3、执行请求
 */
public class LoadBalance {

    /**
     * 先简化逻辑 就用List 表示服务器列表 ，
     * 如需扩展服务器的动态维护功能 需要用Map结构更为合适
     */
    private List<Server> serverList;

    private Selector selector ;

    public LoadBalance(Selector selector){
        this.selector = selector ;
    }

    /**
     *  将服务器集群 注册到负载均衡器中
     * @param serverList  不为空
     */
    public void registerServerList(List<Server> serverList){
        this.serverList = serverList;
        selector.registerServerList(getServerList());
    }

    /**
     *  给一个请求分配一个处理节点 // 让具体子类实现
     * @param request
     * @return
     */
     Server handleRequest(Request request){
         Server server = serverList.get(selector.generateIdx());
         server.handle(request);
         return server;
     }


    public List<Server> getServerList() {
        return serverList;
    }

    public Selector getSelector() {
        return selector;
    }
}
