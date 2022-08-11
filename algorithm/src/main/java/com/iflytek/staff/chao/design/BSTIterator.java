package com.iflytek.staff.chao.design;

import com.iflytek.staff.chao.list.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : hamilton
 * @Description: 二叉树搜索迭代器
 * @date Date : 2022年07月29日 11:01
 */
public class BSTIterator {

    private List<Integer> sList ;

    public BSTIterator(TreeNode root) {
        sList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null|| !stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            sList.add(root.val);
            root=root.right;
        }

    }

    public int next() {
        if(sList.isEmpty()){
            return -1;
        }
        int ans =  sList.get(0);
        sList.remove(0);
        return ans;
    }

    public boolean hasNext() {
        return  !sList.isEmpty();
    }
}
