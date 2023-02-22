package com.iflytek.staff.chao.solution.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: 先进先出方式
 * @date Date : 2023年02月15日 22:47
 */
public class FIFOCache implements Cache {

    int[] cache ;
    Map<Integer,Integer> keyIndexMap ;
    int[] indexToKey;
    int idx ;
    int capacity ;

    public FIFOCache(int capacity) {
        // 控制容量大小
        cache = new int[capacity];
        keyIndexMap = new HashMap<>();
        indexToKey = new int[capacity];
         this.idx =0 ;
         this.capacity = capacity ;
    }

    @Override
    public int get(int key) {
        if(!keyIndexMap.containsKey(key)) return -1 ;
        // 先取得位置指针 ，再拿数据
        int idx = keyIndexMap.get(key);
        return cache[idx];
    }

    @Override
    public void put(int key, int value) {
        if(keyIndexMap.containsKey(key)){
            // 更新对应缓存 ，位置信息不用改变
            int idx = keyIndexMap.get(key);
            cache[idx] = value;

        }else {
            int size = keyIndexMap.size();
            if (size < capacity) {
                // 用map的size做位置指针 递增添加数据
                cache[idx] = value;
                keyIndexMap.put(key, idx);
                indexToKey[idx]=key;
            } else {
                // 覆盖掉idx指向的当前位置数据
                keyIndexMap.remove(indexToKey[idx]);
                cache[idx] = value;
                keyIndexMap.put(key, idx);
                indexToKey[idx]=key;
            }
            // 指针后移一位   循环
            idx = (idx + 1) % capacity;
        }
    }
}
