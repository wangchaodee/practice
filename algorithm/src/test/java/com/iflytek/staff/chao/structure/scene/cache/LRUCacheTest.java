package com.iflytek.staff.chao.structure.scene.cache;


/**
 * @author : wangchaodee
 * @Description:
 * @date Date : 2023年01月10日 20:27
 */
public class LRUCacheTest  {

    public  void testLRUCache() {

        //["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
        //[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]

        LRUCache cache = new LRUCache(3 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);

        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.put(4, 4);
        cache.get(4);       // 返回  1
        cache.get(3);       // 返回 -1 (未找到)
        cache.get(2);
        cache.get(1);
        cache.put(5, 5);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(2);
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
        cache.get(5);
    }
}