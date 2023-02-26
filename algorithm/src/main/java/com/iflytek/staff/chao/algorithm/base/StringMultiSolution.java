package com.iflytek.staff.chao.algorithm.base;

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
}
