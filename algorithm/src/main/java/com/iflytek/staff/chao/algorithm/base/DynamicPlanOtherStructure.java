package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.structure.base.tree.TreeNode;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 动态规划  树形数据 、链表 、或其他类型的数据结构的场景的动态规划
 * @date Date : 2022年07月15日 16:23
 */
public class DynamicPlanOtherStructure {


    /**
     * 打家劫舍 情况三  房子是二叉树型相邻
     *
     * @param root
     * @return
     */
    Map<TreeNode, Integer> choose = new HashMap<>();
    Map<TreeNode, Integer> ignore = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(choose.getOrDefault(root, 0), ignore.getOrDefault(root, 0));
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        dfs(root.right);

        choose.put(root, root.val + ignore.getOrDefault(root.left, 0) + ignore.getOrDefault(root.right, 0));
        ignore.put(root, Math.max(choose.getOrDefault(root.left, 0), ignore.getOrDefault(root.left, 0))
                + Math.max(choose.getOrDefault(root.right, 0), ignore.getOrDefault(root.right, 0)));
    }






}
