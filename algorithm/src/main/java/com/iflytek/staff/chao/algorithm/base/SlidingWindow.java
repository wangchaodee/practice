package com.iflytek.staff.chao.algorithm.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hamilton
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
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] chars= new int[128];
        int left = -1;
        int max =0 ;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            chars[c]++ ;
            while (chars[c]>1){
                chars[s.charAt(++left)]--;
            }
            max = Math.max(max, i - left);
        }
        return max;
    }
}
