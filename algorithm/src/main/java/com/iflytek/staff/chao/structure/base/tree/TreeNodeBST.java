package com.iflytek.staff.chao.structure.base.tree;

import com.iflytek.staff.chao.structure.base.list.ListNode;
import com.iflytek.staff.chao.structure.base.list.ListNodeSolution;

import java.util.Stack;

/**
 * @author : hamilton
 * @Description: 二叉查找树
 * @date Date : 2023年01月12日 12:15
 */
public class TreeNodeBST {

    /**
     * 669. 修剪二叉搜索树
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return  null ;
        if(root.val <low) return trimBST(root.right,low,high);
        if(root.val > high) return trimBST(root.left,low,high) ;
        root.left = trimBST(root.left , low,high);
        root.right = trimBST(root.right ,low,high);
        return  root ;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return root.val;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * @param node
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode  node = root ;
        int pre=0 ;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();

            node.val = node.val + pre ;
            pre = node.val ;
            node = node.left;
        }
        return root;
    }

    /**
     * 108 从有序数组中构造二叉查找树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        int l = 0, r = nums.length - 1;
        return buildTreeNode(nums, 0, r);
    }

    private TreeNode buildTreeNode(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = (r + l) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildTreeNode(nums, l, mid - 1);
        node.right = buildTreeNode(nums, mid + 1, r);
        return node;
    }

    /**
     *109. 根据有序链表构造平衡的二叉查找树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head ==null) return null ;
        if(head.next==null) return  new TreeNode(head.val);
        ListNode preMid = preMiddleNode(head);
        ListNode mid = preMid.next ;
        preMid.next =null ;
        TreeNode ans = new TreeNode(mid.val);
        ans.left = sortedListToBST(head);
        ans.right = sortedListToBST(mid.next);
        return ans ;
    }

    public ListNode preMiddleNode(ListNode head) {
        ListNode oneStep = head, twoStep = head.next;
        ListNode pre=head;
        while (twoStep != null && twoStep.next !=null) {
            pre = oneStep ;
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
        }
        return pre;
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     */
    int min = Integer.MAX_VALUE ;
    TreeNode preNode = null ;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return  min ;
    }

    private void inOrder(TreeNode node ){
        if(node==null) return;
        inOrder(node.left);
        if(preNode !=null) min = Math.min(min,node.val - preNode.val) ;
        preNode = node ;
        inOrder(node.right);
    }



}
