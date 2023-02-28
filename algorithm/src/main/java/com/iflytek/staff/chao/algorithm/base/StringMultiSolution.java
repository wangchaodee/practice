package com.iflytek.staff.chao.algorithm.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
}
