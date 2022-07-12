package com.iflytek.staff.chao.leek;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2022年06月23日 下午4:53
 */
public class CharSolution {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (indexMap.containsKey(s.charAt(i))) {
                left = indexMap.get(s.charAt(i)) + 1;
            } else {
                max = Math.max(max, i - left);
            }
            indexMap.put(s.charAt(i), i);
        }

        return max;
    }

    public boolean isPalindrome(String s) {
        int n = s.length() ;
        int l =0 , r = n-1;
        while (l<r){
            while (l<r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l<r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))){
                return false;
            }else {
                l++;
                r--;
            }
        }
        return true;

    }

    public int countSegments(String s) {

        String[] strs=  s.split(" ");
        int n = strs.length ;
        for(String str : strs){
            if(str.length()==0){
                n--;
            }
        }
        return n;
    }


    public boolean checkInclusion(String s1, String s2) {

        int N1 = s1.length();
        int N2 = s2.length();
        if (N2 < N1) {
            return false;
        }
        int[] dict = new int[26];
        for (int i = 0; i < N1; i++) {
            dict[s1.charAt(i) - 'a']++;
            dict[s2.charAt(i) - 'a']--;
        }

        int diff = 0;
        for (int i = 0; i < 26; i++) {
            if (dict[i] != 0) diff++;
        }

        if (diff == 0) {
            return true;
        }

        for (int i = N1; i < N2; i++) {
            int x = s2.charAt(i) - 'a';
            int y = s2.charAt(i - N1) - 'a';
            if (x == y) {
                continue;
            }
            if (dict[x] == 0) {
                diff++;
            }
            dict[x]++;
            if (dict[x] == 0) {
                diff--;
            }
            if (dict[y] == 0) {
                diff++;
            }
            dict[y]--;
            if (dict[y] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }

        return false;

    }

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int N = chars.length;
        int l = -1, r = -1;
        char t = 0;
        for (int i = 0; i < N; i++) {
            if (chars[i] == ' ') {
                l = r;
                r = i;
                reverseChars(chars, t, l + 1, r - 1);
            }

            if (i == N - 1) {
                l = r;
                r = i;
                reverseChars(chars, t, l + 1, r);
            }

        }

        return new String(chars);
    }

    private void reverseChars(char[] s, char t, int l, int r) {

        while (l < r) {
            t = s[r];
            s[r] = s[l];
            s[l] = t;
            l++;
            r--;
        }
    }

    public void reverseString(char[] s) {
        int N = s.length;
        int l = 0, r = N - 1;
        char t;
        while (l < r) {
            t = s[r];
            s[r] = s[l];
            s[l] = t;
            l++;
            r--;
        }

    }

    public char findTheDifference(String s, String t) {
        int N = s.length();
        int code = t.charAt(0);
        for (int i = 0; i < N; i++) {
            code += t.charAt(i + 1) - s.charAt(i);
        }
        return (char) code;
    }


    /**
     * 字符转换数字
     * @param s
     * @return
     */
    public int myAtoi(String s) {

        return 0 ;
    }
}
