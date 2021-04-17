package com.iflytek.staff.chao.num;

public class Search {



    /**
     * 模拟 错误版本  ， n > 10
     * @param version
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1 ;
        int h = n ;

        while (l<=h) {
            int mid = l + (h - l) / 2;
            if( isBadVersion(mid) ){
                if(!isBadVersion(mid -1)) {
                    return mid ;
                }else {
                    h = mid-1 ;
                }
            }else {
                l =mid+1;
            }
        }
        return  -1 ;

    }

    /**
     * 模拟 错误版本  字方法
     * @param version
     * @return
     */
    boolean isBadVersion(int version) {
        if(version >= 10){
            return true;
        }
        return  false ;
    }

}
