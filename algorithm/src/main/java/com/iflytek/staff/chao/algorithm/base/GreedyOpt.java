package com.iflytek.staff.chao.algorithm.base;

import org.apache.logging.log4j.util.PropertySource;

import java.util.*;

/**
 * @author : hamilton
 * @Description: 贪心算法  最小代价最大成果  最小最大
 * @date Date : 2022年08月05日 16:57
 */
public class GreedyOpt {

    /**
     * 455. 分发饼干
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ig =0 ,is=0 ,gl= g.length ,sl=s.length;
        while (ig<gl && is<sl){
            if(g[ig] <= s[is]){
                ig++;
            }
            is++;
        }
        return ig;
    }

    public int magicTower(int[] nums) {
        int sum = 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 总和不为正  则失败
        if (sum <= 0) return -1;
        // 回到初始值
        long blood = 1;
        PriorityQueue<Integer> pq = new PriorityQueue();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                pq.offer(nums[i]);

                if (blood + nums[i] <= 0) {
                    count++;
                    blood -= pq.poll();
                }

            }
            blood += nums[i];
        }
        return count;
    }

    /**
     * 435. 无重叠区间
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                //选择的区间结尾越小 ，留给后面的区间空间越大 ， 能够计算不重复区间 更快
                return  o1[1] - o2[1];
            }
        });

      /*  int[] dp = new int[intervals.length];
        Arrays.fill(dp, 1);
        int start = 0;
        for (int i = 1; i < intervals.length; i++) {

            for (int j = start; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    start = j;
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return intervals.length - dp[intervals.length - 1];*/

        int cnt =1 ;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i][0]) {
                continue;
            }
            end = intervals[i][1];
            cnt++;
        }
        // 总数 减去 非重合  边界相等只算相邻不算重叠
        return intervals.length -cnt;
    }


    /**
     * 452. 用最少数量的箭引爆气球
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {


 /*       Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //选择的区间结尾越小 ，留给后面的区间空间越大 ， 能够计算不重复区间 更快
//                return  o1[1] - o2[1];  // 有可能溢出  INT.MIN  INT.MAX
                return  o1[1] > o2[1] ? 1: o1[1] < o2[1] ? -1 :0 ;
            }
        });*/

        Arrays.sort(points, Comparator.comparingInt(o->o[1]));

        int cnt =1 ;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            // 边界相等 算重合
            if (end >= points[i][0]) {
                continue;
            }
            end = points[i][1];
            cnt++;
        }
         //返回不重合的数量
        return cnt;
    }

    /**
     * 406. 根据身高重建队列  身高降序  个数升序
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1,o2) ->
                 o1[0] == o2[0] ?  o1[1] - o2[1] : o2[0] - o1[0]
        );

        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1],p);
        }

        return queue.toArray(new int[people.length][]);
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int max =0;
        for (int i = 1; i <prices.length ; i++) {

            if(prices[i]>buy) {
                max=Math.max(max,prices[i]-buy);
            }else {
                buy=prices[i];
            }
        }
        return max;
    }

    /**
     * 122 多次卖卖股票
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int max = 0;
        int m = prices.length;
        int buy = prices[0];
        for (int r = 0; r < m; r++) {
            if (prices[r] > buy) {
                max += prices[r] - buy;
            }
            buy = prices[r];

        }
        return max;
    }

    /**
     * 605. 种花问题
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length ;
        int cnt = 0 ;
        for (int i = 0; i < len; i++) {
            if(flowerbed[i]==1) continue;
            int pre = i==0? 0 : flowerbed[i-1];
            int next = i==len-1? 0 :flowerbed[i+1];
            if(pre==0 && next==0){
                cnt++;
                flowerbed[i]=1;
            }
        }
        return cnt>=n;
    }

    /**
     * 392 判断s 是否是 t 的子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int tl = t.length();
        for (char c : s.toCharArray()) {
            while (i < tl && t.charAt(i) != c) {
                i++;
            }
            if (i == tl) return false;
            i++;
        }
        return true;
    }

    public boolean isSubsequence2(String s, String t) {
        int i = -1;
        int tl = t.length();
        for (char c : s.toCharArray()) {
            i = t.indexOf(c,i+1);
            if(i==-1) return false;
        }
        return true;
    }

    /**
     * 792. 匹配子序列的单词数
     * @param s
     * @param words
     * @return
     */
    public int numMatchingSubseq(String s, String[] words) {
        int cnt = 0 ;
        for (String word : words){
            if(isSubsequence2(word,s)) cnt++;
        }
        return cnt;
    }

    /**
     * 665 数组的改变、移动
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int j = 1; j < n; j++) {
            if (nums[j - 1] > nums[j]) {
                cnt++;

                if (j-2 >= 0 && nums[j-2] > nums[j]) {
                    nums[j] = nums[j - 1];
                }else {
                    nums[j-1] = nums[j];
                }
            }
        }

        return cnt<=1;
    }


    /**
     * 53. 最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = nums[0];
        int max = pre;
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 763. 划分字母区间
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        int N = s.length();
        Map<Character, Integer> charRightIndexMap = new HashMap<>();
        int[] dp = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            char c = s.charAt(i);
            Integer idx = charRightIndexMap.putIfAbsent(c, i);
            if (idx == null) {
                dp[i] = i;
            } else {
                dp[i] = idx.intValue();
            }
        }
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < N) {
            int split = dp[i];
            int j = i;
            while (j <= split) {
                if (dp[j] > split) {
                    split = dp[j];
                }
                j++;
            }
            ans.add(split - i + 1);
            i = split + 1;
        }

        return ans;
    }

    public List<Integer> partitionLabels2(String s) {
        int N = s.length();
        Map<Character, Integer> charRightIndexMap = new HashMap<>();
        int[] dp = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            char c = s.charAt(i);
            Integer idx = charRightIndexMap.putIfAbsent(c, i);
            if (idx == null) {
                dp[i] = i;
            } else {
                dp[i] = idx.intValue();
            }
        }
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < N) {
            int split = dp[i];
            int j = i;
            while (j <= split) {
                if (dp[j] > split) {
                    split = dp[j];
                }
                j++;
            }
            ans.add(split - i + 1);
            i = split + 1;
        }

        return ans;
    }




}
