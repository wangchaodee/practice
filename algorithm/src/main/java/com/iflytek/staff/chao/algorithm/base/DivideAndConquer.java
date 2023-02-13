package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.structure.base.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 分治算法
 * @date Date : 2023年01月18日 21:34
 */
public class DivideAndConquer {

    /**
     * 241. 为运算表达式设计优先级
     *
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        List<Integer> ways = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, n));
                for (Integer l : left) {
                    for (Integer r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }

        if (ways.size() == 0) {
            ways.add(Integer.valueOf(expression));
        }

        return ways;
    }

    /***
     * 95. 不同的二叉搜索树 II
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int s, int e) {
        List<TreeNode> ans = new LinkedList<>();
        if (s > e) {
            ans.add(null);
            return ans;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> left = generateTrees(s, i - 1);
            List<TreeNode> right = generateTrees(i + 1, e);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}
