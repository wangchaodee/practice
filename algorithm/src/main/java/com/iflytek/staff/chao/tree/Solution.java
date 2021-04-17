package com.iflytek.staff.chao.tree;


import java.util.*;

/**
 * Definition for a binary tree node.
 */
public class Solution {

    public static void main(String[] args) {

        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, node7, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        System.out.println(minDepth(node1));

    }


    /**
     * 错误写法
     * @param root
     * @return
     */
//    public static boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        //左节点小于父节点,右节点大于父节点
//        if (root.left != null && root.left.val >= root.val || root.right != null && root.right.val <= root.val ) {
//                return false;
//        }
//
//        return isValidBST(root.left) && isValidBST(root.right);
//
//    }

    public  boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean  isValidBST(TreeNode root,long min ,long max){
        if (root == null) {
            return true;
        }

        if (min >= root.val || max <= root.val ) {
            return false;
        }
        return isValidBST(root.left ,min,root.val) && isValidBST(root.right, root.val, max);
    }


    /**
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 判断是否平衡树
     *
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        return recur(root) != -1;
    }

    /**
     * 后序遍历  剪枝
     *
     * @param node
     * @return
     */
    private int recur(TreeNode node) {
        if (node == null) return 0;
        int left = recur(node.left);
        if (left == -1) return -1;
        int right = recur(node.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }


    /**
     * 计算tree 最小 深度  ，递归方式  深度遍历
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }
        return min + 1;
    }


    /**
     * 计算tree深度  ，递归方式  深度遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 计算tree深度  ，层级遍历
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return res;
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null && !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }

        return list;
    }

}

