package com.iflytek.staff.chao.solution.cache;

import java.util.LinkedHashMap;

/**
 * @author : wangchaodee
 * @Description: 基于LinkedHashMap 实现LRU
 * @date Date : 2023年02月06日 13:39
 */
public class LRUCacheSimple  implements Cache {

    private int capacity ;
    private LinkedHashMap<Integer,Integer>  cache = new LinkedHashMap();

    public LRUCacheSimple(int capacity) {
        this.capacity = capacity ;
    }

    public int get(int key) {
        if(!cache.containsKey(key))
            return -1 ;
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            cache.put(key,value);
            makeRecently(key);
            return;
        }

        if(cache.size() >= this.capacity){
           int oldestKey = cache.keySet().iterator().next();
           cache.remove(oldestKey);
        }
        cache.put(key,value);
    }

    private void makeRecently(int key){
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }
}
