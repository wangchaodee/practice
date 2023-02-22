package com.iflytek.staff.chao.solution.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 随机方式
 * @date Date : 2023年02月15日 22:47
 */
public class RandomCache  implements Cache {

    int[] cache ;
    Map<Integer,Integer> keyToIndex;
    int[] indexToKey;
    private Random random ;
    int capacity ;

    public RandomCache(int capacity) {
        // 控制容量大小
        this.capacity = capacity ;
        cache = new int[capacity];
        keyToIndex = new HashMap<>();
        indexToKey = new int[capacity];
        // 用random 来模拟随机方式
        random = new Random();
    }

    @Override
    public int get(int key) {
        if(!keyToIndex.containsKey(key)) return -1 ;
        // 先取得位置指针 ，再拿数据
        int index = keyToIndex.get(key);
        return cache[index];
    }

    @Override
    public void put(int key, int value) {
        if(keyToIndex.containsKey(key)){
            // 更新对应缓存 ，位置信息不用改变
            int idx = keyToIndex.get(key);
            cache[idx] = value;
        }else {
            int size = keyToIndex.size();
            if (size < capacity) {
                // 用map的size做位置指针 递增添加数据
                cache[size] = value;
                keyToIndex.put(key, size);
                indexToKey[size]=key;
            } else {
                // 随机取得替换位置  先删除索引中的项 ，再覆盖写入并替换
                int idx = random.nextInt(size);
                keyToIndex.remove(indexToKey[idx]);
                cache[idx] = value;
                keyToIndex.put(key, idx);
                indexToKey[idx]=key;
            }
        }
    }
}
