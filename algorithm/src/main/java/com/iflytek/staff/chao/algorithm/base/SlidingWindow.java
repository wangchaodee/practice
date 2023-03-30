package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 滑动窗口
 * @date Date : 2023年02月09日 15:27
 */
public class SlidingWindow {

    /**
     * 424. 替换后的最长重复字符
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        char[] c = s.toCharArray();

        int l =-1  ;
        int[] freq = new int[26];
        int max = 0 ;
        int res = 0 ;

        for (int r = 0; r < n; r++) {
            freq[c[r] - 'A']++ ;
            // 当前最大字符频率
            max = Math.max(max, freq[c[r] - 'A']);
            // 比较窗口长度 和 最大允许值
            if(r-l > max + k){
                freq[c[++l] - 'A']-- ;
            }

            res = Math.max(res, max + k);
        }
        return res  ;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int PL = p.length();
        int SL = s.length();
        List<Integer> ans = new ArrayList<>();
        int[] pchars = new int[127];
        for (char pchar : p.toCharArray()) {
            ++pchars[pchar];
        }
        int prev = -1;
        for (int i = 0; i < SL; i++) {
            int c = s.charAt(i) ;
            // 不符合， 左指正需要移动 ，使得 c 对应的值为1 ，
            while (pchars[c] == 0) {
                int pr = s.charAt(++prev);
                // 将左指针的对应的字符 频率补回去
                ++pchars[pr];
            }
            //扣掉当前字符
            --pchars[c];
            if (i - prev == PL) {
                ans.add(prev + 1);
            }
        }
        return ans;
    }


    /**
     * 567. 字符串的排列
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {

        int N1 = s1.length();
        int N2 = s2.length();
        if (N2 < N1) {
            return false;
        }
        int[] dict = new int[127];
        for (int i = 0; i < N1; i++) {
            dict[s1.charAt(i)]--;
        }

        int pre= -1 ;
        for (int i = 0; i < N2; i++) {
            int x = s2.charAt(i) ;
            while (dict[x]==0){
                dict[s2.charAt(++pre)]--;
            }
                dict[x]++;
            if((i-pre) == N1) return true ;
        }

        return false;
    }



    /**
     * 239. 滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] ans = new int[nums.length - k +1];
        PriorityQueue<int[]> pq = new PriorityQueue<>( ( o1,  o2) -> {
             return o2[0] == o1[0] ? o2[1] - o1[1] : o2[0] - o1[0];
        });
        for (int i = 0; i < k-1 ; i++) {
            pq.offer(new int[]{nums[i],i});
        }

        int j =0 ;
        for (int i = k-1; i < nums.length; i++) {
            pq.offer(new int[]{nums[i],i});
            ans[j++] = pq.peek()[0];
            // 滑动窗口 向右移
            while (!pq.isEmpty() && pq.peek()[1] <= i-k ){
                pq.poll();
            }
        }

        return ans ;
    }

    /**
     * 76. 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if(m<n) return "";
        int[] tf = new int[127] ;
        for (char c : t.toCharArray()) {
            tf[c]++;
        }

        int[] sf = new int[127] ;
        int l =0  , count =0 ,min=m+1 ,start=0  ;
        for (int r = 0; r < m; r++) {
            char c = s.charAt(r) ;
            if(tf[c] == 0 ){
                continue;
            }

            if(sf[c] < tf[c]){
                count++;
            }

            sf[c]++;

            while (count == n){
                if(r-l+1<min){
                    min = r-l+1;
                    start = l;
                }
                char lc = s.charAt(l);
                if(tf[lc]==0) {
                    l++;
                    continue;
                }
                if(sf[lc] == tf[lc]){
                    count--;
                }
                sf[lc]--;
                l++;
            }
        }

        if( min == m +1) {
            return "" ;
        }

        return s.substring(start, start+min);
    }

    /**
     * 2379. 得到 K 个黑块的最少涂色次数
     * @param blocks
     * @param k
     * @return
     */
    public int minimumRecolors(String blocks, int k) {
        int countw = 0 ;
        for (int i = 0; i < k; i++) {
            if(blocks.charAt(i) == 'W') countw++;
        }
        int ans = countw;
        for (int i = k; i <blocks.length() ; i++) {
            if(blocks.charAt(i) == 'W') countw++;
            if(blocks.charAt(i-k) == 'W') countw--;
            ans = Math.min(ans,countw);
        }
        return ans ;
    }
}
