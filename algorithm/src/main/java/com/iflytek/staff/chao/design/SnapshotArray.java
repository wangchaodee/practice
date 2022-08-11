package com.iflytek.staff.chao.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hamilton
 * @Description: 快照数组
 * @date Date : 2022年07月26日 20:34
 */
public class SnapshotArray {

     private  List<int[]>[] array ;
    private int id ;

    public SnapshotArray(int length) {
        array = new List[length];
        for (int i = 0; i < length; i++) {
            array[i] = new ArrayList<int[]>();
        }
        id=0;
    }

    public void set(int index, int val) {
         array[index].add(new int[]{id,val});
    }

    public int snap() {
        int curr = id ;
        id++;
        return curr ;
    }

    public int get(int index, int snap_id) {
        List<int[]> list = array[index];
        int l=-1 ,r = list.size()-1 ;
        while (l<r){
            int mid = (r-l+1)/2+l ;
            if(list.get(mid)[0] <=snap_id) {
               l=mid;
            }else {
                r =mid-1;
            }
        }
        return l>=0 ? list.get(l)[1]:0;
    }

}
