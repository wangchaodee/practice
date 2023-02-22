package com.iflytek.staff.chao.solution.loadbalance;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author : wangchaodee
 * @Description: 一致性哈希方式的负载均衡
 */
public class ConsistentHashSelector implements HashSelector {

    private static final int DEFAULT_VSERVER_NUM =5 ; //150

    private int vServerNum ;
    private int count ;

    private SortedMap<Integer ,Server> vServers = new TreeMap<>();

    public ConsistentHashSelector() {
        this(DEFAULT_VSERVER_NUM) ;
    }

    public ConsistentHashSelector(int vServerNum) {
        this.vServerNum =vServerNum ;
    }


    @Override
    public int generateIdx() {
        //
        return 0;
    }

    @Override
    public Server getByRequest(Request request) {
        int hash = hash(request.getArea());
        SortedMap<Integer,Server> subMap = vServers.tailMap(hash);
        if(!subMap.isEmpty()){
            return subMap.get(subMap.firstKey());
        }
        return vServers.get(vServers.firstKey());
    }

    @Override
    public void registerServerList(List<Server> serverList) {
        count= serverList.size() ;
        for (Server server : serverList) {
            for (int j = 0; j < vServerNum; j++) {
                String vNodeName =server.getId() +"_vnode_" + j;
                vServers.put(hash(vNodeName) , server);
            }
        }
    }

    private int hash(String name){
        return Math.abs(name.hashCode()) % 1023;
    }


}
