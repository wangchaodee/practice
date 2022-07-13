package com.iflytek.staff.chao.array;

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
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;

    }

    public int countSegments(String s) {

        String[] strs = s.split(" ");
        int n = strs.length;
        for (String str : strs) {
            if (str.length() == 0) {
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
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int N = s.length();
        //if(N==0) return 0 ;
        int ans = 0;
        char c;
        boolean negetive = false;// 正负符号
        int num = 0;
        int i=0 ;
        while (i<N && s.charAt(i)==' ') {
            i++;
        }
        if(i>=N) return 0 ;
        if(s.charAt(i)=='+'||s.charAt(i)=='-') {
            if (s.charAt(i) == '-') negetive = true;
            i++;
        }

        while (i < N) {
            c=s.charAt(i);
            i++;
            if(Character.isDigit(c)){
                num = c - '0';
                if (ans < (Integer.MIN_VALUE +num )/ 10) {
                    return Integer.MIN_VALUE;
                }

                if (ans > (Integer.MAX_VALUE-num) / 10) {
                    return Integer.MAX_VALUE;
                }

                if (negetive) {
                    ans = 10 * ans - num;
                } else {
                    ans = 10 * ans + num;
                }
            }else {
                break;
            }
        }

        return ans;
    }

    /**
     * 转换罗马数字
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int N = s.length();
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            int cn = getOne(c);
            if (cn > pre) {
                ans += cn - 2 * pre;
            } else {
                ans += cn;
            }
            pre = cn;
        }
        return ans;
    }

    private int getOne(char one) {
        switch (one) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    private int getTwo(String two) {
        switch (two) {
            case "IV":
                return 4;
            case "IX":
                return 9;
            case "XL":
                return 40;
            case "XC":
                return 90;
            case "CD":
                return 400;
            case "CM":
                return 900;
            default:
                return 0;
        }
    }

    /**
     *   "mississippi"  "issip"
     * "mississippi" "pi"
     * "mississippi" "issipi"
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0 ;

        int nl = needle.length();
        int n =0  ; // n = nl 时 搜索到目标

        int hl = haystack.length();
        for (int i = 0; i < hl; i++) {
            if(haystack.charAt(i)==needle.charAt(n)){
                n++;
                if(n==nl){
                    return i-n+1;
                }
            }else {
                i=i-n+1;
            }
        }
        return -1;
    }

    public String countAndSay(int n) {
        if(n==1) return "1";
        String pre = countAndSay(n-1);
        int N = pre.length();
        int count =1 ;
        int pre_num = pre.charAt(0)-'0';
        int cur_num=0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <N ; i++) {
            cur_num = pre.charAt(i)-'0';
            if(cur_num!=pre_num){
                sb.append(count).append(pre_num);
                pre_num=cur_num;
                count=1;
            }else {
                count++;
            }
        }
        return sb.toString();
    }


    public String longestCommonPrefix(String[] strs) {
        if(strs==null ||strs.length==0 ){
            return "";
        }
        int N = strs.length;
        String prefix = strs[0];
        for (int i = 1; i < N; i++) {
            prefix = commonPrefix(prefix,strs[i]);
            if(prefix.length()==0) break;
        }

        return prefix;
    }

    private String commonPrefix(String prefix, String str) {
        int len = Math.min(prefix.length(),str.length());
        int i =0 ;
        while (i<len){
            if(prefix.charAt(i)==str.charAt(i)){
                i++;
            }else {
                break;
            }
        }
        return prefix.substring(0,i);
    }

}
