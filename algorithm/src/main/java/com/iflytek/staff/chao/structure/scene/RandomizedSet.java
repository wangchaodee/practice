package com.iflytek.staff.chao.structure.scene;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 380. O(1) 时间插入、删除和获取随机元素
 * @date Date : 2023年02月15日 11:41
 */
public class RandomizedSet {

    private Map<Integer,Integer> valueIndex  ;
    private List<Integer> values ;
    private Random random ;

    public RandomizedSet() {
        valueIndex = new HashMap<>();
        values = new ArrayList<>() ;
        random = new Random();
    }

    public boolean insert(int val) {
        if(check(val)) return false ;
        valueIndex.put(val,values.size());
        values.add(val);
        return true ;
    }

    public boolean remove(int val) {
        if(!check(val)) return false;
        int index = valueIndex.get(val);
        int last =values.get(values.size()-1) ;
        values.set(index,last);
        valueIndex.put(last,index);
        values.remove(values.size()-1);
        valueIndex.remove(val);
        return true ;
    }

    public int getRandom() {
        int idx = random.nextInt(values.size());
        return values.get(idx) ;
    }

    private boolean check(int val){
        return valueIndex.containsKey(val);
    }
}
