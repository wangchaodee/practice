package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 贪心算法  最小代价最大成果  最小最大
 * @date Date : 2022年08月05日 16:57
 */
public class GreedyOpt {

    /**
     * 455. 分发饼干
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ig = 0, is = 0, gl = g.length, sl = s.length;
        while (ig < gl && is < sl) {
            if (g[ig] <= s[is]) {
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
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                //选择的区间结尾越小 ，留给后面的区间空间越大 ， 能够计算不重复区间 更快
                return o1[1] - o2[1];
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

        int cnt = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i][0]) {
                continue;
            }
            end = intervals[i][1];
            cnt++;
        }
        // 总数 减去 非重合  边界相等只算相邻不算重叠
        return intervals.length - cnt;
    }


    /**
     * 452. 用最少数量的箭引爆气球
     *
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

        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));

        int cnt = 1;
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
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) ->
                o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]
        );

        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }

        return queue.toArray(new int[people.length][]);
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE;
        int max =0;
        for (int i = 0; i <prices.length ; i++) {
            if(buy < -prices[i]) {
                buy= -prices[i];
            }

            if(max < prices[i]+buy ){
                max=prices[i]+buy;
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
        int buy = prices[0];
        for (int r = 1; r < prices.length; r++) {
            // 获取所有差价的利润
            if (prices[r] > buy) {
                max += prices[r] - buy;
            }
            buy = prices[r];
        }

        return max;
    }

    /**
     * 605. 种花问题
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) continue;
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                cnt++;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    /**
     * 392 判断s 是否是 t 的子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0 ,j=0 ;
        int sl=s.length(),tl = t.length()  ;
        while (i<sl && j<tl) {
            if (s.charAt(i)== t.charAt(j) ) {
                i++;
            }
            j++;
        }
        return i==sl;
    }

    public boolean isSubsequence2(String s, String t) {
        int i = -1;
        int tl = t.length();
        for (char c : s.toCharArray()) {
            i = t.indexOf(c, i + 1);
            if (i == -1) return false;
        }
        return true;
    }

    /**
     * 792. 匹配子序列的单词数
     *
     * @param s
     * @param words
     * @return
     */
    public int numMatchingSubseq(String s, String[] words) {
        int cnt = 0;
        for (String word : words) {
            if (isSubsequence2(word, s)) cnt++;
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

                if (j - 2 >= 0 && nums[j - 2] > nums[j]) {
                    nums[j] = nums[j - 1];
                } else {
                    nums[j - 1] = nums[j];
                }
            }
        }

        return cnt <= 1;
    }


    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int cur = nums[0];
        int max = cur;
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(cur + nums[i], nums[i]);
            max = Math.max(max, cur);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * 763. 划分字母区间
     *
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

        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < N) {
            char c = s.charAt(i);
            int split = s.lastIndexOf(c);
            int j = i + 1;
            while (j < split) {
                char d = s.charAt(j);
                int next = s.lastIndexOf(d);
                if (next > split) {
                    split = next;
                }
                j++;
            }
            ans.add(split - i + 1);
            i = split + 1;
        }

        return ans;
    }

    /**
     * 55 跳跃游戏  贪心算法
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int N = nums.length;
        int i = 0;
        int curMax = nums[0];
        while ( i < curMax && curMax < N-1) {
            i++;
            curMax = Math.max(curMax, i + nums[i]);
        }
        return curMax >= N-1;
    }
    public boolean canJump2(int[] nums) {
        int curMax = 0;
        for (int i = 0; i < nums.length ; i++) {
            if(i>curMax) return false ;
            curMax = Math.max(curMax, i + nums[i]);
        }
        return true;
    }

    /**
     * 908. 最小差值 I
     *
     *
     * 0 <= nums[i] <= 10^4
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeI(int[] nums, int k) {
        int min = 10000;
        int max = 0 ;
        for (int num : nums){
            if(num <min) min = num ;
            if(num > max) max = num ;
        }
        return max -min > 2*k ? max -min -2*k : 0;
    }

    /**
     * 910. 最小差值 II
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeII(int[] nums, int k) {
        int N = nums.length ;
        Arrays.sort(nums);
        int ans = nums[N-1] - nums[0];

        // 假定i 是需要增加k的最大位置， i+1及之后的 都是需要 -k的
        for (int i = 0; i < N-1; i++) {
            int a = nums[i] , b= nums[i+1] ;
            int high = Math.max(nums[N-1] -k , a+k);
            int low = Math.min(nums[0] +k , b-k);
            ans = Math.min(ans,high -low);
        }
        return ans ;
    }

    /**
     * 860. 柠檬水找零
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveCount =0 , tenCount =0 , tweentyCount = 0 ;
        int i = 0 ;
        while (i<bills.length){
            if (bills[i] == 5 ){
                fiveCount++;
            }else if(bills[i]==10){
                if(fiveCount<1) {
                    break;
                }
                fiveCount--;
                tenCount++;
            }else {
                if(tenCount>0 && fiveCount>0){
                        tenCount--;
                        fiveCount--;
                        tweentyCount++;
                }else if(fiveCount>3) {
                    fiveCount -=3;
                    tweentyCount++;
                }else {
                    break;
                }
            }

            i++;
        }
        return  i==bills.length;
    }

    /**
     * 1144. 递减元素使数组呈锯齿状
     * @param nums
     * @return
     */
    public int movesToMakeZigzag(int[] nums) {
        return Math.min(chooseToMake(nums,0) ,chooseToMake(nums,1));
    }

    private int chooseToMake(int[] nums , int start){
        int cnt =0 ;
        for (int i = start; i <nums.length ; i+=2) {
            int a = 0 ; // 使i变为向下的尖刺点 V形 ，需要减掉的数值
            if(i>0){
                a = Math.max(a , nums[i] - nums[i-1] +1 );
            }
            if(i+1<nums.length){
                a = Math.max(a , nums[i] - nums[i+1] +1 );
            }
            cnt +=a ;
        }
        return cnt ;
    }
}
