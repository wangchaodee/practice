package com.iflytek.staff.chao.structure.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hamilton
 * @Description: 新建个哈希表， 支持按时间内序列存储同一个key的值
 * @date Date : 2022年07月25日 22:17
 */
public class TimeMap {

    private Map<String , List<Node>> timeMap ;
    public TimeMap() {
        timeMap= new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<Node> list = timeMap.getOrDefault(key,new ArrayList<>());
        list.add(new Node(key,value,timestamp));
        timeMap.put(key,list);
    }

    public String get(String key, int timestamp) {
        List<Node> list = timeMap.getOrDefault(key,new ArrayList<>());
        if(list.isEmpty()) return "" ;
        int l =0 , r = list.size()-1 ;
        while (l<=r){
            int mid = (r-l)/2 +l ;
            if(list.get(mid).timestamp<= timestamp){
                l=mid+1;
            }else {
                r=mid-1;
            }
        }
        if(r<0) return "";
        return list.get(r).value;
    }

}

class Node {
    String key ;
    String value ;
    int timestamp ;

    public Node(String key, String value, int timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }
}
