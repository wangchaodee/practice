package com.iflytek.staff.chao.structure.scene;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author : wangchaodee
 * @Description: 729. 我的日程安排表 I
 * @date Date : 2023年02月15日 14:41
 */
public class MyCalendar {

    TreeSet<int[]> books ;

    public MyCalendar() {
        books  = new TreeSet<>((a,b)-> a[0] - b[0]) ;
    }

    public boolean book(int start, int end) {

        Iterator<int[]> it =  books.iterator();
        while (it.hasNext()){
            int[] e = it.next() ;
            if(e[1] <= start){
                // 自动后移
                continue;
            }
            // 区域重合
            if(e[0] < end && start < e[1]) {
                return false;
            }
            // 超过区域
            if(e[0] >= end){
                break;
            }
        }
        books.add(new int[]{start,end});
        return true ;
    }

    class SegmentTree{
        int left ;
        int right ;

    }
}
