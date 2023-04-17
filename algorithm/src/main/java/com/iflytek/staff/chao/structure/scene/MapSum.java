package com.iflytek.staff.chao.structure.scene;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: 剑指 Offer II 066. 单词之和
 */
public class MapSum {

   /* Map<String,Integer> map ;
    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }

    public int sum(String prefix) {
        int res = 0 ;
        for(String key : map.keySet()){
            if(key.startsWith(prefix)){
                res +=map.get(key);
            }
        }
        return res ;
    }*/

    Map<String,Integer> map ;
    Map<String ,Integer> prefixMap ;
    public MapSum() {
        map = new HashMap<>();
        prefixMap = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key,0);
        map.put(key,val);
        for (int i = 1; i <= key.length(); i++) {
            String curPrefix = key.substring(0,i);
            prefixMap.put(curPrefix, prefixMap.getOrDefault(curPrefix,0)+delta);
        }
    }

    public int sum(String prefix) {
        return prefixMap.getOrDefault(prefix,0);
    }

}
