package com.iflytek.staff.chao.util;

import com.iflytek.staff.chao.structure.base.list.ListNode;
import com.iflytek.staff.chao.structure.base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : wangchaodee
 * @Description: 173 二叉树搜索迭代器
 * @date Date : 2022年07月29日 11:01
 */
public class BSTIterator {

    private List<Integer> sList;

  /*  public BSTIterator(TreeNode root) {
        sList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            sList.add(root.val);
            root = root.right;
        }

    }

    public int next() {
        if (sList.isEmpty()) {
            return -1;
        }
        int ans = sList.get(0);
        sList.remove(0);
        return ans;
    }

    public boolean hasNext() {
        return !sList.isEmpty();
    }*/

    int idx ;
    public BSTIterator(TreeNode root) {
        sList = TreeNode.inorderTraversal2(root);
        idx =0 ;
    }

    public int next() {
        return sList.get(idx++);
    }

    public boolean hasNext() {
        return idx<sList.size();
    }
}
