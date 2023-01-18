package com.iflytek.staff.chao.interview;

/**
 * @author : hamilton
 * @Description: 虚拟竞赛
 * @date Date : 2023年01月14日 10:22
 */
public class Competion230114 {

    /**2437. 有效时间的数目
     *
     * @param time
     * @return
     */
    public int countTime(String time) {
        String[] parts = time.split(":");
        return countTime(parts[0] ,24) * countTime(parts[1],60);
    }

    private int countTime(String part , int max){
        if(!part.contains("?")) return 1;
        char[] chars = part.toCharArray() ;
        if(chars[1]=='?' && chars[0] =='?' ) {
            return max;
        } else if(chars[1]=='?'&& chars[0] !='?')  {
            if(max==24 && chars[0]=='2') return 4 ;
            return 10;
        } else  {
            if(max==60) return 6;
            if(max==24 && chars[1] > '3'){
                return 2;
            }else {
                return 3;
            }
        }
    }

}
