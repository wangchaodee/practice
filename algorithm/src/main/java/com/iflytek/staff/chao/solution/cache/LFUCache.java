package com.iflytek.staff.chao.solution.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author : wangchaodee
 * @Description: 460  请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * @date Date : 2023年02月06日 13:51
 */
public class LFUCache  implements Cache{

    // time 代表时间器
    int capacity, time;
    Map<Integer, Node> cache;

    // 统计数据 以树形排序
    TreeSet<Node> statistic;

    public LFUCache(int capacity) {
        if(capacity == 0 ) throw new IllegalArgumentException();

        this.capacity = capacity ;
        this.time = 0 ;
        cache = new HashMap<>();
        statistic = new TreeSet<>() ;
    }

    public int get(int key) {

        if(!cache.containsKey(key))
            return -1 ;

        Node node = cache.get(key);

        statistic.remove(node);

        node.cnt +=1;
        // 更新时间戳
        node.time = ++time ;

        statistic.add(node);
        cache.put(key,node);

        return node.value;
    }

    public void put(int key, int value) {

        if(!cache.containsKey(key)){

            if(cache.size()==capacity){
                cache.remove(statistic.first().key);
                statistic.remove(statistic.first());
            }

            Node node = new Node(key,value ,1, ++time);
            cache.put(key,node);
            statistic.add(node);
        }else {
            Node node = cache.get(key);
            statistic.remove(node);

            node.cnt +=1;
            // 更新时间戳
            node.time = ++time ;
            node.value = value ;

            statistic.add(node);
            cache.put(key,node);
        }
    }

    class Node implements Comparable<Node> {
        // cnt 代表使用次数， time 最近的一次时间  值越大代表越近期
        int key, value, cnt, time;

        public Node(int key, int value, int cnt, int time) {
            this.key = key;
            this.value = value;
            this.cnt = cnt;
            this.time = time;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (other instanceof Node) {
                Node node = (Node) other;
                return this.cnt == node.cnt && this.time == node.time;
            }

            return false;
        }

        @Override
        public int compareTo(Node o) {
            return cnt == o.cnt ? time - o.time : cnt - o.cnt;
        }

        public int hashCode() {
            return cnt * 1000000007 + time;
        }
    }
}
