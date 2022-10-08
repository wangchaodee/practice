package com.iflytek.staff.chao.array;

import java.util.*;

/**
 * @author : hamilton
 * @Description: 字符串类的 算法判断
 * @date Date : 2022年07月31日 18:01
 */
public class StringSolution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>>  strMap = new HashMap<>();

        for (String str :strs) {
            String key = getKey(str);
            List<String> list= strMap.getOrDefault(key,new ArrayList<>());
            list.add(str);
            strMap.put(key, list);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> value : strMap.values()){
            ans.add(value);
        }
        return  ans;
    }

    private String getKey(String str){
        if(str.length()==0) return str;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    /**
     * KMP 算法  获取k的值
     * @param ps
     * @return
     */
    public int[] getNext(String ps){
        int N = ps.length() ;
        char[] arr = ps.toCharArray();
        int[] next = new int[N];
        int j= 0 ;
        int k= -1 ;
        next[j] = k ;
        while (j<N-1){
            if(k==-1 || arr[j] == arr[k]){
                next[++j] = ++k;
            }else {
                k=next[k];
            }
        }
        return next;
    }


    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        int nl = needle.length();
        int n = 0;

        int hl = haystack.length();
        int i=0;

        int[] next = getNext(needle);

        while (i<hl && n<nl) {
            if (n==-1|| haystack.charAt(i) == needle.charAt(n)) {
                n++;
                i++;
            } else {
                n = next[n];
            }
        }
        if (n == nl) {
            return i - n ;
        }
        return -1;
    }

    /**
     * "mississippi"  "issip"
     * "mississippi" "pi"
     * "mississippi" "issipi"
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        int nl = needle.length();
        int n = 0; // n = nl 时 搜索到目标

        int hl = haystack.length();
        int i=0;
        while (i<hl && n<nl) {
            if (haystack.charAt(i) == needle.charAt(n)) {
                n++;
                i++;
            } else {
                i = i - n+1;
                n = 0;
            }
        }
        if (n == nl) {
            return i - n + 1;
        }
        return -1;
    }


    public boolean repeatedSubstringPattern(String s) {
        int N = s.length() ;
        if(N<=1) return false;
        // 至少是重复个两遍的
        for (int i = 1; i*2 <=N ; i++) {
            //是否为整数倍数
            if(N%i==0){
                boolean match = true ;
                for (int j = i; j < N; j++) {
                    if(s.charAt(j) != s.charAt(j-i)){
                        // 代表 字段长度i 的不是正确解
                        match= false;
                        break;
                    }
                }
                // 代表 长度i的字串 是一种重复的字串
                if(match) {
                    return true;
                }
            }
        }
        return  false;
    }


    public String addBinary(String a, String b) {

        int add =0 ;
        int i = a.length() -1 ;
        int j = b.length() -1 ;

        // 以逆序的方式 存入结果  结果需要翻转
        StringBuilder ans = new StringBuilder();
        while (i>=0 || j >=0 || add >0){
            int a1 = i>=0 ? a.charAt(i) -'0' : 0 ;
            int b1 = j>=0 ? b.charAt(j) - '0' : 0 ;

            int t = a1 + b1 + add ;
            //位计算结果
            ans.append(t%2);

            //进位值
            add = t/2;
            //指针迭代
            i--;
            j--;
        }
        return  ans.reverse().toString();
    }

    /**
     * 反转字符串中的单词
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] strlist = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strlist.length-1; i >=0; i--) {
            if(strlist[i].length()==0) continue;
            sb.append(strlist[i]).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
