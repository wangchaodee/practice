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
}
