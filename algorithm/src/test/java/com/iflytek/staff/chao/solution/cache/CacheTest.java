package com.iflytek.staff.chao.solution.cache;

import com.iflytek.staff.chao.algorithm.scene.ShuffleArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : wangchaodee
 * @Description: 对cache命中率的模拟测试
 * @date Date : 2023年02月16日 10:34
 */
public class CacheTest {

    @Test
    public void testCache(){
        int count = 20000 ;
        int randomMax = 50 ;
        int capacity = 10 ;
//        int[] array = new int[count];
        int[] array = new int[2*count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = random.nextInt(randomMax);
        }

        // 将小区间值增大
        randomMax /=2 ;
        for (int i = 0; i < count; i++) {
            array[i] = random.nextInt();
        }
        // 随机化数组 打乱 将前后两部分数据混合均匀
        ShuffleArray shuffleArray = new ShuffleArray(array);
        array = shuffleArray.shuffle();

        List<Cache> caches = new ArrayList<>() ;
        caches.add(new FIFOCache(capacity));
        caches.add(new RandomCache(capacity));
        caches.add(new LRUCache(capacity));
        caches.add(new LFUCache(capacity));
        for (int i = 0; i < caches.size(); i++) {
            Cache cache = caches.get(i) ;
            int cnt = testCache(array , cache);
            System.out.printf(" %s 的命中率为 : %.3f , \n" ,cache.getClass().getName() , (float)cnt/(2*count) );
        }
    }


    /**
     *
     * @param array  用非负数的数组作为测试集: array[i]的值作为key , array[i]+1 代表value
     * @param cache
     */
    private int testCache(int[] array , Cache cache){
        int cnt =0 ; // 统计缓存命中次数
        for (int i = 0; i < array.length; i++) {
            if( cache.get(array[i]) == -1 ) {
                cache.put(array[i] ,array[i]+1);
            }else if(cache.get(array[i]) == array[i]+1 ) {
                cnt++;
            }else {
                // 缓存数据错误 检查代码后测试
                System.out.printf("缓存数据错误 检查代码后测试 key: %s ,value: %s \n"  ,array[i] ,cache.get(array[i]) );
            }
        }
        return cnt ;
    }


}