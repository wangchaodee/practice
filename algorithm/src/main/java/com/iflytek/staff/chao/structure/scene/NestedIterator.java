package com.iflytek.staff.chao.structure.scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 * @date Date : 2023年02月15日 09:04
 */
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> list;
    private int index = 0;


    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList();
        add( nestedList);
    }

    private void add(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                list.add(ni.getInteger());
            } else {
                add(ni.getList());
            }
        }
    }


    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return (index < list.size());
    }

    // 示意
    class NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 1;

        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }
}
