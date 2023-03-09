package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 多个字符串  或字符数组
 */
public class StringMultiSolution {

    /**
     * 1247. 交换字符使得字符串相同
     * @param s1
     * @param s2
     * @return
     */
    public int minimumSwap(String s1, String s2) {
        int l = s1.length() ;
        int xy = 0 ,yx=0 ;
        for (int i = 0; i < l; i++) {
            char a = s1.charAt(i);
            char b = s2.charAt(i) ;
            if(a=='x' && b=='y' ){
                xy++;
            }
            if(a=='y' && b=='x' ){
                yx++;
            }
        }
        if((xy+yx) % 2 ==1 ) {
            return -1 ;
        }

        return xy/2 + yx /2 + xy%2 + yx %2 ;
    }

    /**720. 词典中最长的单词
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        Arrays.sort(words ,(a,b) ->{
            if(a.length() != b.length()){
                return a.length() - b.length();
            }else {
                return b.compareTo(a);
            }
        });
        String longest = "";
        Set<String> valids = new HashSet<>();
        valids.add("");
        for (String word : words) {
            if(valids.contains(word.substring(0,word.length()-1))){
                valids.add(word);
                longest = word;
            }
        }
        return longest;
    }

    /**
     * 1487. 保证文件名唯一
     * @param names
     * @return
     */
    public String[] getFolderNames(String[] names) {
        int n = names.length ;
        String[] ans = new String[n] ;
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (map.containsKey(name)){
               int k = map.get(name);
               while (map.containsKey(addSuffix(name,k))){
                   k++;
               }
                map.put(name,k);
                map.put(addSuffix(name,k),1);
                ans[i] = addSuffix(name,k);
            }else {
                map.put(name,1);
                ans[i] = name;
            }
        }
       return ans ;
    }

    private String addSuffix(String name ,int k){
        return  name +"("+k+")" ;
    }

    /**
     * 2131. 连接两字母单词得到的最长回文串
     * @param words
     * @return
     */
    public int longestPalindrome(String[] words) {
        Map<String,Integer> counts = new HashMap<>();
        for(String word : words){
            counts.put(word, counts.getOrDefault(word,0)+1);
        }
        int len =0 ;
        boolean mid = false ;
        for(Map.Entry<String,Integer> word : counts.entrySet()){
            String change = reverse(word.getKey());
            if(change.equals(word.getKey())){
                // 字符相同
                if(word.getValue() %2 ==1 ) {
                    mid = true;
                }
                len += ((word.getValue()/2) *2 ) *2 ;
            }else {
                int min = Math.min(word.getValue() , counts.getOrDefault(change,0)) *2 ;
                len +=min ;
            }
        }
        if(mid) {
            len+=2;
        }
        return len ;

    }

    private String reverse(String word){
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }

    /**
     * 面试题45. 把数组排成最小的数
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n] ;
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs ,(x,y) -> (x+y).compareTo(y+x));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

}
