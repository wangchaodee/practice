package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.structure.base.list.ListNode;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 优先队列场景的算法应用
 * @date Date : 2023年02月08日 21:22
 */
public class PriorityQueueSolution {

    /**
     * 剑指 Offer II 078. 合并排序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0 || (lists.length==1 && lists[0]==null)){
            return null;
        }
        int k = lists.length ;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(k, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                // 默认的小顶堆
                return o1.val - o2.val ;
            }
        });

        ListNode ans = new ListNode(-1);
        //设定指针
        ListNode pre = ans ;
        // 放入堆中 自然排序
        for (int i = 0; i < k; i++) {
            if(lists[i] !=null){
                pq.offer(lists[i]);
            }
        }
        while (!pq.isEmpty()){
            ListNode min = pq.poll();
            pre.next = min ;
            pre = min;

            // 替代后续的 放入堆中
            if(min.next != null){
                pq.offer(min.next);
                min.next = null;
            }
        }
        return ans.next;
    }

    /**
     * 1046. 最后一块石头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(stones.length, Comparator.reverseOrder());
        for(int stone : stones){
            pq.offer(stone);
        }
        while (pq.size() >1 ){
            int a = pq.poll() ;
            int b = pq.poll() ;
            if(a-b >0 ) {
                pq.offer(a - b);
            }
        }
        return pq.size() == 0 ? 0 : pq.poll();
    }


    /**
     * 692. 前K个高频单词
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for (String word :words) {
            map.put(word, map.getOrDefault(word, 0) +1);
        }

        PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue() ;
            }
        });

        for (Map.Entry<String,Integer> entry : map.entrySet()){
            pq.offer(entry);
        }

        while (pq.size()>k){
            pq.poll();
        }

        List<String > ans = new ArrayList<>();
        while (!pq.isEmpty()){
            ans.add(pq.poll().getKey());
        }
        return ans ;
    }


}
