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
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || (lists.length == 1 && lists[0] == null)) {
            return null;
        }
        int k = lists.length;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(k, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                // 默认的小顶堆
                return o1.val - o2.val;
            }
        });

        ListNode ans = new ListNode(-1);
        //设定指针
        ListNode pre = ans;
        // 放入堆中 自然排序
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                pq.offer(lists[i]);
            }
        }
        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            pre.next = min;
            pre = min;

            // 替代后续的 放入堆中
            if (min.next != null) {
                pq.offer(min.next);
                min.next = null;
            }
        }
        return ans.next;
    }

    /**
     * 1046. 最后一块石头的重量
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(stones.length, Comparator.reverseOrder());
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a - b > 0) {
                pq.offer(a - b);
            }
        }
        return pq.size() == 0 ? 0 : pq.poll();
    }


    /**
     * 692. 前K个高频单词
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
            }
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }

        while (pq.size() > k) {
            pq.poll();
        }

        List<String> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().getKey());
        }
        return ans;
    }


    /**
     * 1792. 最大平均通过率
     *
     * @param classes
     * @param extraStudents
     * @return
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            long v1 = (long) (a[1] - a[0]) * (b[1] + 1) * b[1];
            long v2 = (long) (b[1] - b[0]) * (a[1] + 1) * a[1];
            if (v1 == v2) return 0;
            return v1 < v2 ? 1 : -1;
        });

        for (int[] c : classes) {
            queue.offer(new int[]{c[0], c[1]});
        }

        for (int i = 0; i < extraStudents; i++) {
            int[] c = queue.poll();
            queue.offer(new int[]{c[0] + 1, c[1] + 1});
        }
        double d = 0;
        for (int i = 0; i < classes.length; i++) {
            int[] c = queue.poll();
            d += 1.0 * c[0] / c[1];
        }
        return d / classes.length;
    }

    /**
     * 218. 天际线问题
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 放入建筑的左右坐标
        List<Integer> indexs = new ArrayList<>();
        for (int[] x : buildings) {
            indexs.add(x[0]);
            indexs.add(x[1]);
        }
        Collections.sort(indexs);

        List<List<Integer>> ans = new ArrayList<>();
        int n = buildings.length, idx = 0;
        // 以高度排序   大顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int xpoint : indexs) {
            while (idx < n && buildings[idx][0] <= xpoint) {
                queue.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }

            while (!queue.isEmpty() && queue.peek()[0] <= xpoint) {
                queue.poll();
            }

            int maxn = queue.isEmpty() ? 0 : queue.peek()[1];
            // 为空  或者高度不一样
            if (ans.size() == 0 || maxn != ans.get(ans.size() - 1).get(1)) {
                // 放入的左侧点 坐标 和高度
                ans.add(Arrays.asList(xpoint, maxn));
            }
        }
        return ans;

    }

    /**
     * 621. 任务调度器
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> count = new HashMap<>();
        int maxExec = 0;
        for (char c : tasks) {
            int freq = count.getOrDefault(c, 0) + 1;
            count.put(c, freq);
            maxExec = Math.max(maxExec, freq);
        }
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() == maxExec) {
                maxCount++;
            }
        }

        return Math.max((maxExec - 1) * (n - 1) + maxCount, tasks.length);
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 数字 频率
        Map<Integer, Integer> dataCount = new HashMap<>();
        for (int num : nums) {
            dataCount.put(num, dataCount.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (Map.Entry<Integer, Integer> data : dataCount.entrySet()) {
            if (queue.size() < k) {
                queue.offer(new int[]{data.getKey(), data.getValue()});
            } else {
                if (data.getValue() > queue.peek()[1]) {
                    queue.poll();
                    queue.offer(new int[]{data.getKey(), data.getValue()});
                }
            }
        }

        int[] ans = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            ans[i++] = queue.poll()[0];
        }
        return ans;
    }

    /**
     * 剑指 Offer II 061. 和最小的 k 个数对
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length, n = nums2.length;
        int min = Math.min(m, k);
        for (int i = 0; i < min; i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idx = pq.poll();
            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[idx[0]]);
            pair.add(nums2[idx[1]]);
            ans.add(pair);
            if (idx[1] + 1 < n) {
                pq.offer(new int[]{idx[0], idx[1] + 1});
            }
        }
        return ans;
    }

}
